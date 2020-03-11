package com.cts.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cts.model.CustomerDetails;
import com.cts.model.RechargePackage;
import com.cts.service.RechargeService;

@Controller
public class RechargeController {
	@Autowired
	private RechargeService rechargeService;

	// add an initbinder ... to convert trim input strings
	// remove leading and trailing whitespace
	// resolve issue for our validation
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping("/")
	public ModelAndView show() {
		CustomerDetails customerDetails = new CustomerDetails();
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("customerDetails", customerDetails);
		return modelAndView;
	}

	@RequestMapping("/check")
	public String checkStatus(@Valid @ModelAttribute("customerDetails") CustomerDetails customerDetails,
			BindingResult result, Model model) {
		String viewName = null;
		String mobileNo = customerDetails.getMobileNo();
		Date previousRechargeDt = null;
		String packageName = customerDetails.getPreviousSelectedPackage();
		String previousReachargeDate = customerDetails.getPreviousRechargeDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
		try {
			previousRechargeDt = sdf.parse(previousReachargeDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean active = rechargeService.checkStatus(customerDetails);
		if (result.hasErrors()) {
			viewName = "index";
		} else {

			if (!rechargeService.checkCarrier(mobileNo, packageName)) {
				System.out.println("hello");
				model.addAttribute("msg", "Your mobile and Package Selected are different");
				viewName = "index";
			}

			if (active) {
				System.out.println("hi");
				model.addAttribute("msg", "Your mobile validity is not expired");
				viewName = "index";
			} else {
				List<RechargePackage> rechargePackages = rechargeService.getAllPackagesByPlanName(packageName);
				model.addAttribute("packages", rechargePackages);
				viewName = "list";
			}
		}
		return viewName;
	}

	@RequestMapping("/detail/{pid}")
	public String showDetailPage(@PathVariable("pid") int pid, Model model) {
		System.out.println("hello");
		RechargePackage pack = rechargeService.getPackage(pid);
		model.addAttribute("pack", pack);
		System.out.println("hi");
		return "details";
	}
}
