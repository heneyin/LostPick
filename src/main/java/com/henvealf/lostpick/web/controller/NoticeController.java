package com.henvealf.lostpick.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.henvealf.lostpick.data.NoticeRepository;
import com.henvealf.lostpick.data.UploadImage;
import com.henvealf.lostpick.data.UserRepository;
import com.henvealf.lostpick.security.OperationSecurity;
import com.henvealf.lostpick.utils.CookieUtils;
import com.henvealf.lostpick.utils.FileUtils;
import com.henvealf.lostpick.web.beans.Notice;
import com.henvealf.lostpick.web.beans.User;
import com.henvealf.lostpick.web.services.NoticeService;




@Controller
@RequestMapping("/notice")
public class NoticeController extends NoticeService{
	
	private NoticeRepository noticeRepository;
	private UserRepository userRepository;
	
	@Autowired
	public NoticeController(NoticeRepository noticeRepository,
							UserRepository userRepository){
		super(noticeRepository);
		this.noticeRepository = noticeRepository;
		this.userRepository = userRepository;
	}
	
	/**
	 * 展示所有的lost
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/lost/{pageNumber}", method=GET)
	public String lost(Model model,
				@PathVariable ("pageNumber") int pageNumber){
		model.addAttribute("titlePrefix", "寻物启事");
		model.addAttribute("word","丢失");
		model.addAttribute("lop","lost");
		model.addAttribute("page", pageNumber);
		model.addAttribute("maxPage",noticeRepository.getMaxPageCountByLopType(Notice.LOST));
		model.addAttribute("noticeList",
			noticeRepository.findAllNotice(Notice.LOST,pageNumber));
		
		model.addAttribute("pageName","notices");
		return "notices";
	}
	
	/**
	 * 展示所有的pick
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/pick/{pageNumber}", method=GET)
	public String pick(Model model,
			@PathVariable ("pageNumber") int pageNumber){
		model.addAttribute("titlePrefix", "失物招领");
		model.addAttribute("word","捡到");
		model.addAttribute("lop","pick");
		
		model.addAttribute("page", pageNumber);
		model.addAttribute("maxPage",noticeRepository.getMaxPageCountByLopType(Notice.PICK));
		
		model.addAttribute("noticeList",
				noticeRepository.findAllNotice(Notice.PICK,pageNumber));
		model.addAttribute("pageName","notices");
		return "notices";
	}
	
	/**
	 * 根据物品类型获得启事
	 * @param
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/lost/{goodsType}/{pageNumber}", method=RequestMethod.GET)
	public String lostbyType(Model model,
			@PathVariable("goodsType") String type,
			@PathVariable("pageNumber") int pageNumber
			){
		model.addAttribute("titlePrefix", "寻物启事");
		model.addAttribute("word","丢失");
		model.addAttribute("lop","lost");
		
		model.addAttribute("page", pageNumber);
		model.addAttribute("maxPage",noticeRepository.getMaxPageCountByLopAndGoodsType(Notice.LOST, type));
		
		model.addAttribute("noticeList",
				noticeRepository.findByGoodsType(type,Notice.LOST,pageNumber));
		
		model.addAttribute("pageName","notices");
		return "notices";
	}
	
	@RequestMapping(value="/pick/{goodsType}/{pageNumber}", method=RequestMethod.GET)
	public String pickbyType(Model model,
			@PathVariable("goodsType") String type,
			@PathVariable("pageNumber") int pageNumber){
		model.addAttribute("titlePrefix", "寻物启事");
		model.addAttribute("word","丢失");
		model.addAttribute("lop","lost");
		
		model.addAttribute("page", pageNumber);
		model.addAttribute("maxPage",noticeRepository.getMaxPageCountByLopAndGoodsType(Notice.PICK, type));
		
		model.addAttribute("noticeList",
				noticeRepository.findByGoodsType(type,Notice.PICK,pageNumber));
		
		model.addAttribute("pageName","notices");
		return "notices";
	}
	
	/**
	 * <p>修改操作之前，跳转到修改表单<p>
	 * <p>bupdate = before update<p>
	 * <p>upInfo 修改表单所需要的必要的信息 格式为 notice.id_userId <p>
	 * 
	 * @param model
	 * @param
	 * @return
	 */
	@RequestMapping(value="/bupdate/{upInfo}",method=GET)
	public String beforeUpdate(Model model,
			@PathVariable ("upInfo") String upInfo){
		//得到notice的Id
		String noticeIdStr = upInfo.substring(0, upInfo.indexOf('_'));
		long noticeId = Long.parseLong(noticeIdStr);
		//得到userId
		String userId = upInfo.substring(upInfo.indexOf('_')+1, upInfo.length());
		
		if(!OperationSecurity.isLoginUser(userId, model)){	//检查作者
			return "error/error";
		}
		
		Notice oldNotice = noticeRepository.findOne(noticeId); 				//获取旧的
		model.addAttribute("goodsTypeOptions",NoticeService.getGoodsTypeOptions());
		model.addAttribute("pageName","addNotice");
		model.addAttribute("notice",oldNotice);
		//跳转到修改表单
		return "updateNotice";
	}
	
	/**
	 * 执行修改
	 * @param model
	 * @param
	 * @return
	 */
	@RequestMapping(value="/update",method = POST)
	public String update(	@RequestPart("imageFile") Part noticePicture,
							Notice notice,
							Errors errors,
							HttpServletRequest request,
							Model model,
							RedirectAttributes flashModel){
		
		if(!OperationSecurity.isLoginUser(notice.getUserId(), model)){	//检查作者
			return "error/error";
		}
		System.out.println(notice.getId());
		System.out.println(notice.getUserId());
		if(errors.hasErrors()){
			return "redirect:/notice/bupdate/"+notice.getId()+"_"+notice.getUserId();
		}
		boolean isHaveImage = Boolean.parseBoolean(request.getParameter("isHaveImage"));
		boolean isChangeImg;
		if(!isHaveImage){			//没改变
			isChangeImg = false;
		}else{									//图片改变
			isChangeImg = true; 
			String dbImgUrl = UploadImage.goCompressAndUpload(noticePicture, notice);//压缩，上传
			String oldImgUrl = noticeRepository.findOne(notice.getId()).getImgUrl();
			FileUtils.deleteSingleFile(oldImgUrl);  //删除老图片
			notice.setImgUrl(dbImgUrl);	//修改链接
		}
		notice.setLopTime(notice.produceLopTime());   //丢失或者捡到时的时间，启事原始发布时间不改变
		noticeRepository.updateInfo(notice,isChangeImg);
		flashModel.addFlashAttribute("toastValue","修改成功");
		return "redirect:/user/personalCenter/"+notice.getUserId();
	}
	
	/**
	 * 删除表单,需要验证权限
	 * @param
	 * @return
	 */
	@RequestMapping(value="/delete/{upInfo}",method=GET)
	public String delete(	@PathVariable ("upInfo") String upInfo,
							HttpServletRequest req,
							Model model,
							RedirectAttributes flashModel){
		//得到notice的Id 
		String noticeIdStr = upInfo.substring(0, upInfo.indexOf('_'));
		long noticeId = Long.parseLong(noticeIdStr);
		//得到userId
		String userId = upInfo.substring(upInfo.indexOf('_')+1, upInfo.length());
		if(!OperationSecurity.isLoginUser(userId, model)){
			return "error/error";
		} 
		
		//删除操作
		String imgUrl = noticeRepository.findOne(noticeId).getImgUrl();
		noticeRepository.delete(noticeId);
		FileUtils.deleteSingleFile(imgUrl);
		//获得进入详细页面的来路。
		String backUrl = CookieUtils.getCookieValue(req, "backUrl", "/user/personalCenter/" + userId);
		
		//返回来路所在页面
		System.out.println("backUrl: " + backUrl);
		flashModel.addFlashAttribute("toastValue", "删除成功");
		//return "redirect:/user/personalCenter/" + userId;
		return "redirect:" + backUrl;
	}

	/**
	 * 根据ID访问单个notice详情，并记录来路路径在cookie中，以备删除时返回。
	 * @param noticeId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/detail/{noticeId}", method=RequestMethod.GET)
	public String detailNotice(
							@PathVariable ("noticeId") long noticeId,
							Model model,
							HttpServletRequest req,
							HttpServletResponse resp
							){
		
		Notice notice = noticeRepository.findOne(noticeId);
		User user = userRepository.findByUserId(notice.getUserId());
		model.addAttribute("notice",notice);
		model.addAttribute("user",user);
		if(notice.isLostOrPick() == Notice.LOST){
			model.addAttribute("titlePrefix", "寻物启事详情");
			model.addAttribute("word","丢失");
		}else {
			model.addAttribute("titlePrefix", "失物招领详情");
			model.addAttribute("word","捡到");
		}
		
		//使用Cookie记录下来到详细表单的页面的地址。是用户的个人中心，还是展示启事的页面。
		String toDetailLastPageUri = req.getHeader("referer");
		System.out.println("进入详细页面的页面路径： " + toDetailLastPageUri);
		toDetailLastPageUri = toDetailLastPageUri.
				substring(toDetailLastPageUri.indexOf("lost-pick-spring")+"lost-pick-spring".length());
		//System.out.println("---DetailsServlet--toDetailLastPageUrl-: " + toDetailLastPageUri);
		System.out.println("切掉头之后的路径：" + toDetailLastPageUri);  
		Cookie ck = new Cookie("backUrl", toDetailLastPageUri);
		//时效为60*60秒,只能作用于删除启事上。
		//ck.setMaxAge(60*60);
		//ck.setPath("/lost-pick-spring/notice/delete/*");
		//放入报头
		resp.addCookie(ck);
		return "noticeDetail"; 
	}
	
	
	/**
	 * 展示添加物品的表单
	 * @param
	 * @param model
	 * @return 
	 */
	@RequestMapping(value="/addNotice", method = GET)
	public String showAddNoticeForm(Model model){
		String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();
		Notice notice = new Notice();
		notice.setUserId(loginUserId);            //添加用户Id
		model.addAttribute("notice",notice);      //进行验证是这里千万要new一个
		model.addAttribute("pageName","addNotice");
		model.addAttribute("goodsTypeOptions",NoticeService.getGoodsTypeOptions());
		return "addNotice";
	}
	
	/**
	 * 执行添加新启事的操作
	 * @param notice
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/addNotice", method = POST)
	public String processAddNoticeForm(
								@RequestPart("imageFile") Part noticePicture,
								@Valid Notice notice, 
								Model model,
								RedirectAttributes flashModel,
								HttpServletRequest request,
								Errors errors ) throws UnsupportedEncodingException,Exception{
		// 是否有图片
		boolean isHaveImage = Boolean.parseBoolean(request.getParameter("isHaveImage"));
		
		if(!OperationSecurity.isLoginUser(notice.getUserId(), model)){
			return "error/error";
		} 
		
		if(errors.hasErrors()){
			model.addAttribute("notice",notice);
			model.addAttribute("pageName","addNotice");
			model.addAttribute("goodsTypeOptions",NoticeService.getGoodsTypeOptions());
			return "addNotice"; //返回添加页面
		}
		 
		//上传并返回保存数据库中的图片链接
		//String dbImgUrl = UploadImage.goUpload(cutLocation, noticePicture, newNotice);
		String dbImgUrl = null;
		if(isHaveImage){		//有图片,上传图片
			dbImgUrl = UploadImage.goCompressAndUpload(noticePicture, notice);//压缩，上传
		}else{					//没图片
			dbImgUrl="null";
		} 
		
		notice.setImgUrl(dbImgUrl);							//保存在数据库中的图片链接
		notice.setNoticeTime(NoticeService.getNowTime());	//启事发布时间
		notice.setLopTime(notice.produceLopTime());			//丢或者捡的时间

		noticeRepository.add(notice);
		//System.out.println("添加启事成功");
		flashModel.addFlashAttribute("toastValue","添加启事成功");
		String lop = null;
		if(notice.isLostOrPick() == Notice.LOST){
			lop = "lost";
		}else{
			lop="pick";
		}
		return "redirect:/notice/"+ lop +"/1";
	}
	
	/**
	 * 捕捉所有经过该控制器的DuplicateNoticeException
	 * @return
	 
	@ExceptionHandler(Exception.class)
	public String handleDuplicateNotice(Model model){
		model.addAttribute("errorMessage","未知错误 ");
		return "error/error";
	}*/
}
