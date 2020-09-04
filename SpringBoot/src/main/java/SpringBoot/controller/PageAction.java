package SpringBoot.controller;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
@Component
public class PageAction {
	public void page(Model model, int count, int limit,int limitPage , int page , String pageUrl) {
		int maxPage = (int)((double)count/limit + 0.95); //web에 보이는 member에 따른 마지막 page
		int startPage = (int)(((double)page / limitPage + 0.95) -1) * limitPage + 1;   //web에 보이는 페이지의 첫 수
		int endPage =  startPage + limitPage -1 ;									   //web에 보이는 페이지의 마지막 수
		if(endPage > maxPage) endPage = maxPage;	//member에 따른 페이지만 보이게 하기 위한 값, 멤버수보다 endPage값이 크면 값이 null이므로
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("page", page);
		model.addAttribute("pageUrl", pageUrl);
	}
}