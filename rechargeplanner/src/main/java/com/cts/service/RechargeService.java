package com.cts.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cts.exceptions.InvalidReachargeDateException;
import com.cts.model.CustomerDetails;
import com.cts.model.RechargePackage;

@Service
public class RechargeService {
	private List<RechargePackage> packages;

	public RechargeService() {
		packages = new ArrayList<RechargePackage>();
		packages.add(new RechargePackage(1, "Airtel ABC Plan", "airtel", 30, 399));
		packages.add(new RechargePackage(2, "Airtel xyz Plan", "airtel", 45, 500));
		packages.add(new RechargePackage(3, "Bsnl ABC Plan", "bsnl", 30, 299));
		packages.add(new RechargePackage(4, "Bsnl xyz Plan", "bsnl", 60, 500));
		packages.add(new RechargePackage(5, "cellone ABC Plan", "cellone", 30, 350));
		packages.add(new RechargePackage(6, "Cellone xyz Plan", "cellone", 45, 500));
	}

	public boolean checkStatus(CustomerDetails customerDetails) {
		int validity = 0;
		boolean active = false;
		Date previousRechargeDt = null;
		String previousReachargeDate = customerDetails.getPreviousRechargeDate();
		String previousSelectedPackage = customerDetails.getPreviousSelectedPackage();
		for (RechargePackage rechargePackage : packages) {
			if (rechargePackage.getPackageName().equals(previousSelectedPackage)) {
				validity = rechargePackage.getValidity();
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			previousRechargeDt = sdf.parse(previousReachargeDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(previousRechargeDt);
		calendar.add(Calendar.DATE, validity);
		Date nextRechargeDate = calendar.getTime();
		System.out.println("prev"+previousReachargeDate);
		System.out.println("next"+nextRechargeDate);
		Date currentDate = new Date();
		if (previousRechargeDt.compareTo(currentDate) > 0) {
			System.out.println(previousRechargeDt.compareTo(currentDate) > 0);
			active = false;
			throw new InvalidReachargeDateException("Invalid Reacharge Date");
		}
		else if (nextRechargeDate.compareTo(currentDate) > 0) {
			active = true;
		}

		// System.out.println(active);
		return active;

	}

	public List<RechargePackage> getAllPackagesByPlanName(String packageName) {
		List<RechargePackage> packs;
		String carrier = null;
		for (RechargePackage pack : packages) {
			if (pack.getPackageName().equalsIgnoreCase(packageName)) {
				carrier = pack.getCarrierType();
			}
		}
		packs = getAllPackages(carrier);
		return packs;

	}

	public List<RechargePackage> getAllPackages(String carrierType) {
		List<RechargePackage> packs = new ArrayList<RechargePackage>();
		for (RechargePackage pack : packages) {
			if (pack.getCarrierType().equalsIgnoreCase(carrierType)) {
				packs.add(pack);
			}
		}
		return packs;
	}

	public LinkedHashMap<String, String> getPackageNames() {
		LinkedHashMap<String, String> packageNames = new LinkedHashMap<String, String>();
		for (RechargePackage pack : packages) {
			packageNames.put(pack.getPackageName(), pack.getPackageName());
		}
		return packageNames;
	}

	public RechargePackage getPackage(int pid) {
		RechargePackage selectedPackage = null;
		for (RechargePackage pack : packages) {
			if (pack.getId() == pid)
				selectedPackage = pack;
		}
		return selectedPackage;
	}

	public RechargePackage getPackageByname(String PlanName)

	{

		RechargePackage selectedPackage = null;

		for (RechargePackage pack : packages)

		{

			if (pack.getPackageName() == PlanName)

			{

				selectedPackage = pack;

			}

		}

		return selectedPackage;

	}

	public boolean checkCarrier(String mobilenumber, String planName) {
		boolean b = false;
		RechargePackage selectedPackage = getPackageByname(planName);

		String carrierType = selectedPackage.getCarrierType();

		// 91=>Airtel

		// 86=>Bsnl

		// 75=>Cellone

		String firstTwoNumber = mobilenumber.substring(0, 2);

		switch (firstTwoNumber) {

		case "91":

			System.out.println("Airtel");
			b = true;

			break;

		case "86":

			System.out.println("Bsnl");
			b = true;
			break;

		case "75":

			System.out.println("CellOne");
			b = true;
			break;

		default:

			System.out.println("Please Check Your Number");

			break;

		}
		return b;
	}

}
