package com.spring.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dao.UserRepository;
import com.spring.entity.User;
import com.spring.service.ForgotPasswordAdminService;
import com.spring.service.ForgotPasswordUserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ForgotPasswordControllerAdmin {
	
	Random random = new Random();

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ForgotPasswordAdminService forgotPasswordAdminService;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping("/forgot-password-admin")
	public String openForgotPassword() {
		return "admin_forgot_password/forgot_password_form";
	}

	@PostMapping("/send-otp-admin")
	public String sendOTP(@RequestParam("email") String email, HttpSession session) {
		System.out.println("EMAIL " + email);

		int otp = random.nextInt(9000) + 1000;

		System.out.println("OTP " + otp);

		String subject = "This OTP sent from Smart Admission Management System";
		String message = "" + "<div style='border:1px solid #e2e2e2; padding:20px'>" + "<h2>" + "Your OTP is " + "<b>"
				+ otp + "</n>" + "</h2>" + "</div>";
		String to = email;

		boolean flag = this.forgotPasswordAdminService.sendEmail(subject, message, to);

		if (flag) {
			session.setAttribute("myotp", otp);
			session.setAttribute("email", email);

			return "admin_forgot_password/verify_otp";
		} else {

			session.setAttribute("message", "Check your email id !");

			return "admin_forgot_password/forgot_password_form";
		}

	}

	@PostMapping("/verify-otp-admin")
	public String verifyOTP(@RequestParam("otp") int otp, HttpSession session) {

		int myotp = (int) session.getAttribute("myotp");
		String email = (String) session.getAttribute("email");

		if (myotp == otp) {

			User user = this.userRepo.findByEmail(email);

			if (user == null) {

				session.setAttribute("msg", "User does't exist with this email address !");
				return "admin_forgot_password/forgot_password_form";
			} else {

				return "admin_forgot_password/change_password";
			}

		} else {

			session.setAttribute("msg", "You have entered wrong OTP !");

			return "admin_forgot_password/verify_otp";
		}

	}

	// change password after otp verification

	
	@PostMapping("/change-password-admin/{id}")
	public String changePassword(@RequestParam("newPassword") String newPassword, HttpSession session) {
		String email = (String) session.getAttribute("email");
		User user = this.userRepo.findByEmail(email);
		user.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
		this.userRepo.save(user);

		session.setAttribute("msg", "Your password have been changed successfully ! You can login now !");

		return "admin_forgot_password/change_password";

		/*
		 * return
		 * "redirect:/signin?change=password changed successfully ! You can login now !"
		 * ;
		 */

	}


}
