package com.henvealf.lostpick.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.henvealf.lostpick.data.NoticeRepository;
import com.henvealf.lostpick.data.UserRepository;
import com.henvealf.lostpick.security.HasRealNameUser;
import com.henvealf.lostpick.security.OperationSecurity;
import com.henvealf.lostpick.utils.EncryptUtil;
import com.henvealf.lostpick.web.beans.LoginForm;
import com.henvealf.lostpick.web.beans.Notice;
import com.henvealf.lostpick.web.beans.User;
import com.henvealf.lostpick.web.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends UserService{
	
	private UserRepository userRepository;
	private NoticeRepository noticeRepository;
	@Autowired
	public UserController(UserRepository userRepository,
							NoticeRepository noticeRepository) {
		super(userRepository);
		this.userRepository = userRepository;
		this.noticeRepository = noticeRepository;
	}
	
	@RequestMapping(value="/register", method = GET)
	public String showRegisterForm(Model model){
		
		model.addAttribute("user",new User());				 //空User
		model.addAttribute("pageName","register");	 	   	 //	
		model.addAttribute("academys",UserService.getAllAcademys());  //用在name为academy的select中
		return "register";
	}
	
	@RequestMapping(value="/register", method = POST )
	public String processRegistration(
						@Valid User user, 					
						Errors errors,
						Model model,
						HttpServletRequest request,
						HttpSession session,
						RedirectAttributes flashModel){
		
		String codePicture = request.getParameter("codePicture") ; //验证码
		String trueCode = session.getAttribute("codePicture").toString();
		//如果输入格式有错，返回注册页面
		if(errors.hasErrors() || !codePicture.equalsIgnoreCase(trueCode)){
			model.addAttribute("user",user);						      //之前填写的数据
			model.addAttribute("pageName","register");	 	   			  //	页面名字
			model.addAttribute("academys",UserService.getAllAcademys());  //用在name为academy的select中
			model.addAttribute("pictureCodeError", true);
			
			//检查验证码
			if(!codePicture.equalsIgnoreCase(trueCode)){//验证码错误
				model.addAttribute("codeMessage","验证码有误！");
			}else {										//验证码对了，但其他表单有误
				model.addAttribute("codeMessage","请重新输入！");
			}
			return "register";
		}

/* 		try {
			profilePicture.write
			("E:\\My Progarm\\Java Web\\lost-pick-spring\\src\\main\\webapp\\user\\data\\"
			+profilePicture.getSubmittedFileName() );
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		//加密
		String password = EncryptUtil.encrypt(user.getPassword());
		user.setPassword(password);
		userRepository.add(user);		//添加到数据库
		flashModel.addFlashAttribute("toastValue","注册成功");
		return "redirect:/home";
	}
	
	/**
	 * 展示登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login", method = GET)
	public String showLoginForm(Model model){
		model.addAttribute("loginForm",new LoginForm());
		model.addAttribute("toastValue","请登录");
		model.addAttribute("pageName","login");
		return "login";
	}

	/**
	 * 获取用户信息，包括发布的启事。到个人中心页面
	 * @param userId 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/personalCenter/{userId}", method= GET)
	public String showUserInfo(
			@PathVariable String userId, Model model){
		
		//如果访问用户不是该页面的作者
		if(!OperationSecurity.isLoginUser(userId, model)){
			return "error/error";
		}
		
		User user = userRepository.findByUserId(userId);
		if(user == null){
			throw new UserNotFoundException();
		}
		List<Notice> lostNotices = noticeRepository.findNoticesByUserId(userId,Notice.LOST);
		List<Notice> pickNotices = noticeRepository.findNoticesByUserId(userId,Notice.PICK);
		model.addAttribute("userInfo",user);
		model.addAttribute("lostNotices",lostNotices);
		model.addAttribute("pickNotices",pickNotices);
		model.addAttribute("titlePrefix", user.getUserName());   //要改成名字
		return "personalCenter";
		
	}
	
	/**
	 * cpf check password form
	 * @param model
	 * @param userId
	 * @param
	 * @return
	 */
	@RequestMapping(value="/cpf/{userId}")
	public String checkOldPasswordFrom(Model model,
			@PathVariable ("userId") String userId){
		//如果访问用户不是该页面的作者
		if(!OperationSecurity.isLoginUser(userId, model)){
			
			return "error/error";
		}
		model.addAttribute("step",1);
		return "updatePassword";
	}
	
	/**
	 * cp check password
	 * @return
	 */
	@RequestMapping(value="/cp/{userId}")
	public String checkOldPassword(Model model,
							@PathVariable ("userId") String userId,
							LoginForm loginForm,
							RedirectAttributes flashModel,
							int step){
		//如果访问用户不是该页面的作者
		if(!OperationSecurity.isLoginUser(userId, model)){
			return "error/error";
		}
		//填充UserId
		loginForm.setUserId(userId);
		//System.out.println("step:" + step);
		if(step == 1){    //检查密码
			if(userRepository.checkLogin(loginForm)){
				model.addAttribute("step",step + 1);
				System.out.println("检查用户成功！");
				model.addAttribute("pageName","register");
				return "updatePassword";
			}else{
				System.out.println("检查用户失败！");
				flashModel.addFlashAttribute("error","密码错误！");
				return "redirect:/user/cpf/" + userId;
			}
		}else if(step == 2){		//修改
			//加密
			String password = EncryptUtil.encrypt(loginForm.getPassword());
			loginForm.setPassword(password);
			userRepository.updatePassword(loginForm);
			SecurityContextHolder.clearContext();
			return "redirect:/user/login";
		}
		
		model.addAttribute("errorMessage","未知错误!");
		return "error/error";
		
	}
	
	@RequestMapping(value="updateInfo/{userId}", method = GET)
	public String beforeUpdateInfo(Model model,
								@PathVariable ("userId") String userId){
		if(!OperationSecurity.isLoginUser(userId, model)){
			return "error/error";
		}
		//获取旧信息
		User user = userRepository.findByUserId(userId);
		model.addAttribute("pageName","register");
		user.setPassword(null);//将密码清除
		model.addAttribute("user", user);
		model.addAttribute("academys",UserService.getAllAcademys());  //用在name为academy的select中
		return "updateUserInfo";
	}
	
	@RequestMapping(value="updateInfo/{userId}", method = POST)
	public String processUpdateInfo(
								Model model,
								RedirectAttributes flashModel,
								@PathVariable ("userId") String userId,
								@Valid User user,
								Errors errors){
		if(!OperationSecurity.isLoginUser(userId, model)){
			return "error/error";
		}
		//修改失败
		if(errors.hasErrors()){
			//model.addAttribute("pageName","register");
			model.addAttribute("user", user);
			model.addAttribute("academys",UserService.getAllAcademys());  //用在name为academy的select中
			return "updateUserInfo";
		}
		
		//修改成功
		userRepository.updateInfo(user);
		HasRealNameUser userDetails = (HasRealNameUser) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		userDetails.setUserRealName(user.getUserName());
		flashModel.addFlashAttribute("toastValue","修改成功");
		return "redirect:/user/personalCenter/" + user.getUserId();
	}
	
	@RequestMapping(value="/checkRepeat/{userId}", method=GET)
	public void checkUserIdCanUse(HttpServletRequest request,
									HttpServletResponse response,
									@PathVariable ("userId") String userId){
		try {
			PrintWriter writer = response.getWriter();
			boolean result = userRepository.checkUserIdCanUse(userId);
			System.out.println("result" + result);
			writer.print(result);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		
	}
	
	
	/**
	 * 捕捉所有经过该控制器的DuplicateNoticeException
	 * @return
	 
	@ExceptionHandler(Exception.class)
	public String handleDuplicateNotice(Model model){
		model.addAttribute("errorMessage","未知错误");
		return "error/error";
	}*/
}
