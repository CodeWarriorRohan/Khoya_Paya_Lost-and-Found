/*
 * package com.spring.controller;
 * 
 * import java.security.Principal; import java.util.List; import
 * java.util.Optional; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * com.spring.dao.SignUpStudentRepo; import
 * com.spring.dao.StudentEducationalRepository; import
 * com.spring.dao.StudentDetailsRepository; import
 * com.spring.dao.UserRepository; import com.spring.entity.SignUp_Student;
 * import com.spring.entity.Student_E_DETAILS; import
 * com.spring.entity.Students_Details; import com.spring.entity.User; import
 * com.spring.service.SignUp_StudentService; import
 * com.spring.service.StudentEductionalService; import
 * com.spring.service.StudentDetailsService;
 * 
 * import jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpServletResponse; import
 * jakarta.servlet.http.HttpSession;
 * 
 * @Controller
 * 
 * @RequestMapping("/user") public class UserController {
 * 
 * @Autowired private UserRepository userRepo;
 * 
 * @Autowired private SignUpStudentRepo signupRepo;
 * 
 * @Autowired private SignUp_StudentService signupservice;
 * 
 * @Autowired private StudentDetailsService spservice;
 * 
 * @Autowired private StudentDetailsRepository sprepo;
 * 
 * @Autowired private StudentEducationalRepository serepo;
 * 
 * @Autowired private StudentEductionalService seservice;
 * 
 * @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
 * 
 * @ModelAttribute public void commonUser(Principal p, Model m) { if (p != null)
 * { String email = p.getName(); User user = userRepo.findByEmail(email);
 * m.addAttribute("user", user); }
 * 
 * }
 * 
 * @ModelAttribute public void commonStudent(Principal p, Model m) { if (p !=
 * null) { String email = p.getName(); SignUp_Student signup_student =
 * signupRepo.findByEmail(email); m.addAttribute("signup_student",
 * signup_student); }
 * 
 * }
 * 
 * @GetMapping("/home") public String home() { return "user/user_dashboard"; }
 * 
 * @GetMapping("/profile") public String profile() { return "user/profile"; }
 * 
 * @GetMapping("/signup_student") public String signupStudent(Model m) {
 * m.addAttribute("title", "SignUp Student");
 * 
 * return "user/signup_student"; }
 * 
 * @GetMapping("/user_dashboard") public String userDashboard(Model m) {
 * m.addAttribute("title", "Home"); m.addAttribute("stpinfo", new
 * Students_Details());
 * 
 * return "user/user_dashboard"; }
 * 
 * @GetMapping("/add_student_personal") public String
 * openAddStudentPersonal(Model m) {
 * 
 * m.addAttribute("title", "Add Student"); m.addAttribute("stpinfo", new
 * Students_Details());
 * 
 * return "user/add_student_personal"; }
 * 
 * @GetMapping("/add_student_education") public String
 * openAddStudentEducation(Model m) { m.addAttribute("title", "Add Student");
 * m.addAttribute("steinfo", new Student_E_DETAILS());
 * 
 * return "user/add_student_edu"; }
 * 
 * @GetMapping("/view_students_personal") public String
 * openViewStudentPersonal(Model m) {
 * 
 * List<Students_Details> stpinfo = spservice.getAllStudentsPersonalDetails();
 * 
 * m.addAttribute("title", "View Students"); m.addAttribute("stpinfo", stpinfo);
 * 
 * return "user/view_students_personal"; }
 * 
 * @GetMapping("/view_students_educational") public String
 * openViewStudentEducational(Model m) {
 * 
 * List<Student_E_DETAILS> steinfo =
 * seservice.getAllStudentsEducationalDetails(); m.addAttribute("title",
 * "View Students"); m.addAttribute("steinfo", steinfo);
 * 
 * return "user/view_students_educational"; }
 * 
 * 
 * @GetMapping("/update_students_personal/{s_id}") public String
 * openUpdateStudentPersonal(@PathVariable int s_id, Model m) {
 * 
 * m.addAttribute("title", "Update Students"); Students_Details stp =
 * spservice.getStudentPersonalById(s_id); m.addAttribute("stinfo", stp);
 * 
 * return "user/update_students_personal"; }
 * 
 * @GetMapping("/update/{s_id}") public String UpdateStudent(@ModelAttribute
 * Students_Details stp, HttpSession session) {
 * 
 * spservice.addStudentPersonal(stp); session.setAttribute("msg",
 * "One Student's personal details updated successsfully !");
 * 
 * return "user/user_dashboard"; }
 * 
 * @GetMapping("/delete/{s_id}") public String deleteStudent(@PathVariable int
 * s_id, HttpSession session) {
 * 
 * spservice.deleteStudentPersonal(s_id); session.setAttribute("msg",
 * "One Student's personal details deleted successsfully !");
 * 
 * return "user/user_dashboard"; }
 * 
 * 
 * 
 * @GetMapping("/update_students_educational/{s_id}") public String
 * openUpdateStudenteducational(@PathVariable int s_id, Model m) {
 * 
 * m.addAttribute("title", "Update Students"); Student_E_DETAILS ste =
 * seservice.getStudentEducationalById(s_id); m.addAttribute("stinfo", ste);
 * 
 * return "user/update_students_educational"; }
 * 
 * @GetMapping("/update_edu/{s_id}") public String
 * UpdateStudentEdu(@ModelAttribute Student_E_DETAILS ste, HttpSession session)
 * {
 * 
 * seservice.addStudentEducational(ste); session.setAttribute("msg",
 * "One Student's educational details updated successsfully !");
 * 
 * return "user/user_dashboard"; }
 * 
 * @GetMapping("/delete_edu/{s_id}") public String
 * deleteStudentEdu(@PathVariable int s_id, HttpSession session) {
 * 
 * seservice.deleteStudentEducational(s_id); session.setAttribute("msg",
 * "One Student's educational details deleted successsfully !");
 * 
 * return "user/user_dashboard"; }
 * 
 * @PostMapping("/save_student_personal") public String
 * saveStudentPersonal(@ModelAttribute Students_Details studentpersonal,
 * HttpSession session) {
 * 
 * System.out.println(studentpersonal);
 * spservice.addStudentPersonal(studentpersonal);
 * 
 * session.setAttribute("msg",
 * "One Student's personal details added successfully !");
 * 
 * return "user/add_student_edu"; }
 * 
 * @PostMapping("/save_student_educational") public String
 * saveStudentEducational(@ModelAttribute Student_E_DETAILS studenteducational,
 * HttpSession session) {
 * 
 * System.out.println(studenteducational);
 * seservice.addStudentEducational(studenteducational);
 * 
 * session.setAttribute("msg",
 * "One Student's personal and educational details added successfully !");
 * 
 * return "user/user_dashboard"; }
 * 
 * @GetMapping("/exportPersonalDetails") public void
 * generateExcelReport(HttpServletResponse response) throws Exception{
 * response.setContentType("application/octet-stream"); String headerKey
 * ="Content-Disposition"; String headerValue
 * ="attachment;filename=Student_P_INFO.xls"; response.setHeader(headerKey,
 * headerValue);
 * 
 * spservice.generateExcel(response); }
 * 
 * @GetMapping("/exportEducationalDetails") public void
 * generateExcelReportEdu(HttpServletResponse response) throws Exception{
 * response.setContentType("application/octet-stream"); String headerKey
 * ="Content-Disposition"; String headerValue
 * ="attachment;filename=Student_E_DEAILS.xls"; response.setHeader(headerKey,
 * headerValue);
 * 
 * seservice.generateExcel(response); }
 * 
 * @RequestMapping("/show_student_personal/{s_id}") public String
 * openShowStudentPersonal(@PathVariable("s_id") Integer s_id, Model m) {
 * 
 * m.addAttribute("title", "Show Student"); m.addAttribute("stpinfo", new
 * Students_Details());
 * 
 * Optional<Students_Details> stpOptional = this.sprepo.findById(s_id);
 * Students_Details stpinfo = stpOptional.get();
 * 
 * m.addAttribute("stpinfo",stpinfo);
 * 
 * 
 * return "user/show_student_personal"; }
 * 
 * 
 * @RequestMapping("/show_student_educational/{s_id}") public String
 * openShowStudentEducational(@PathVariable("s_id") Integer s_id, Model m) {
 * 
 * m.addAttribute("title", "Show Student"); m.addAttribute("steinfo", new
 * Student_E_DETAILS());
 * 
 * Optional<Student_E_DETAILS> steOptional = this.serepo.findById(s_id);
 * Student_E_DETAILS steinfo = steOptional.get();
 * 
 * m.addAttribute("steinfo",steinfo);
 * 
 * return "user/show_student_educational"; }
 * 
 * @GetMapping("/change_user_password") public String userPassword(Model m) {
 * m.addAttribute("title", "Change User Password");
 * 
 * return "user/change_user_password"; }
 * 
 * @PostMapping("/change-user-password") public String
 * changeUserPassword(@RequestParam("oldPassword") String
 * oldPassword, @RequestParam("newPassword") String newPassword, Principal p,
 * HttpSession session) {
 * 
 * session.setAttribute("msg",
 * "Your User password have been changed successfully !");
 * 
 * System.out.println("OLD PASSWORD "+oldPassword);
 * System.out.println("NEW PASSWORD "+newPassword);
 * 
 * String userName = p.getName(); User currentUser =
 * this.userRepo.findByEmail(userName);
 * System.out.println(currentUser.getPassword());
 * 
 * if(this.bCryptPasswordEncoder.matches(oldPassword,
 * currentUser.getPassword())) {
 * currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
 * this.userRepo.save(currentUser);
 * 
 * }else {
 * 
 * }
 * 
 * return "user/user_dashboard"; }
 * 
 * @GetMapping("/change_student_password") public String userPassword(Model m,
 * HttpSession session) { m.addAttribute("title", "Change Student Password");
 * 
 * return "user/change_student_password"; }
 * 
 * 
 * 
 * Search coding
 * 
 * 
 * @GetMapping("/filterper") public String FilterStudentPersonal(Model m ,
 * String keyword) { List<Students_Details> stpinfo =
 * spservice.getAllStudentsPersonalDetails();
 * 
 * if(keyword != null) {
 * m.addAttribute("stpinfo",spservice.findByKeyword(keyword)); } else {
 * m.addAttribute("stpinfo",stpinfo); }
 * 
 * return "user/view_students_personal"; }
 * 
 * @GetMapping("/filterperE") public String FilterStudent(Model m , String
 * keyword) { List<Student_E_DETAILS> steinfo =
 * seservice.getAllStudentsEducationalDetails();
 * 
 * if(keyword != null) {
 * m.addAttribute("steinfo",seservice.findByKeyword(keyword)); } else {
 * m.addAttribute("stpinfo",steinfo); }
 * 
 * return "user/view_students_educational"; }
 * 
 * 
 * @RequestMapping("/admission_letter/{s_id}") public String
 * generateAdmissionLetter(@PathVariable("s_id") Integer S_id, Model m) {
 * 
 * m.addAttribute("title", "Show Student"); m.addAttribute("stpinfo", new
 * Students_Details());
 * 
 * Optional<Students_Details> stpOptional = this.sprepo.findById(S_id);
 * Students_Details stpinfo = stpOptional.get();
 * 
 * m.addAttribute("stpinfo",stpinfo);
 * 
 * 
 * return "user/admission_letter"; }
 * 
 * @RequestMapping("/admission_slip/{s_id}") public String
 * generateAdmissionSlip(@PathVariable("s_id") Integer S_id, Model m) {
 * 
 * m.addAttribute("title", "Show Student"); m.addAttribute("stpinfo", new
 * Students_Details());
 * 
 * Optional<Students_Details> stpOptional = this.sprepo.findById(S_id);
 * Students_Details stpinfo = stpOptional.get();
 * 
 * m.addAttribute("stpinfo",stpinfo);
 * 
 * 
 * return "user/admission_slip"; }
 * 
 * @PostMapping("/saveStudent") public String saveStudent(@ModelAttribute
 * SignUp_Student s_student, HttpSession session, HttpServletRequest request) {
 * 
 * 
 * String url = request.getRequestURI().toString();
 * 
 * url = url.replace(request.getServletPath(), "");
 * 
 * 
 * SignUp_Student signup = signupservice.saveStudent(s_student, url);
 * 
 * if (signup != null) { session.setAttribute("msg",
 * "Student Registered successfully ! And Password has been sent on registered mobile number !"
 * ); // System.out.println("save success!"); } else {
 * session.setAttribute("msg", "Something wrong on server!");
 * System.out.println("error!"); }
 * 
 * return "user/signup_student"; } }
 */

package com.spring.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



import com.spring.dao.UserRepository;
import com.spring.entity.Post;
import com.spring.entity.User;
import com.spring.service.PostImpl;
import com.spring.service.UserServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;


	@Autowired
	private UserServiceImp userService;

	@Autowired
	private PostImpl postservice;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			User user = userRepo.findByEmail(email);
			m.addAttribute("user", user);
		}

	}

////show image in html table
	@GetMapping("/view_users/{id}")
	public ResponseEntity<byte[]> getEmployeeImage(@PathVariable int id) {
		User user = userService.getUserById(id);

		if (user != null && user.getImageData() != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG); // Adjust content type as needed
			System.out.println("image upload");
			return new ResponseEntity<>(user.getImageData(), headers, HttpStatus.OK);
		}
		System.out.println("image not uplode");
		// Return a placeholder image or an error image if needed
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/home")
	public String home() {
		return "admin_dashboard";
	}

	@GetMapping("/profile")
	public String adminProfile() {
		return "user/profile";
	}

	@GetMapping("/fileupoad")
	public String formate() {
		return "user/fileupoad";
	}

	@GetMapping("/user_dashboard")
	public String userDashboard(Model m) {
		m.addAttribute("title", "Home");
		   			
     m.addAttribute("User", new User());
			 
		return "user/user_dashboard";
	}
	
	@GetMapping("/post")
	public String homeIndex() {
		return "user/post";
	}

	
	
	
	// for image
//	ALTER TABLE EMP_SYSTEM
//	MODIFY COLUMN image_data LONGBLOB; --

		@GetMapping("/change_user_password")
	public String adminPassword(Model m, HttpSession session) {
		m.addAttribute("title", "Change User Password");

		return "user/change_user_password";
	}

	@PostMapping("/change-user-password")
	public String changeUserPassword(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, Principal p, HttpSession session) {

		session.setAttribute("msg", "Your password have been changed successfully !");

		System.out.println("OLD PASSWORD " + oldPassword);
		System.out.println("NEW PASSWORD " + newPassword);

		String userName = p.getName();
		User currentUser = this.userRepo.findByEmail(userName);
		System.out.println(currentUser.getPassword());

		if (this.bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword())) {
			currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
			this.userRepo.save(currentUser);

		} else {

		}

		return "user/user_dashboard";
	}
	
//	@PostMapping("/savePost")
//	public String savePost(@ModelAttribute Post post, HttpSession session, HttpServletRequest request,@RequestParam("file") MultipartFile file) {
//
//		String url = request.getRequestURI().toString();
//
//		url = url.replace(request.getServletPath(), "");
//		try {
//			if (!file.isEmpty()) {
//
//				long MAX_FILE_SIZE = 10 * 1024 * 1024;
//				if (file.getSize() <= MAX_FILE_SIZE) {
//
//					byte[] imageData = file.getBytes();
//					post.setImageData(imageData);
//				}
//			}
//		Post u = postservice.saveUser(post, url);
//
//		if (u != null) {
//			session.setAttribute("msg", "Register successfully!"); //
//			System.out.println("save success!");
//		} else {
//			session.setAttribute("msg", "Something wrong on server!");
//			System.out.println("error!");
//		}
//	} catch (IOException e1) {
//		// Handle the exception
//	}
//		return "redirect:/";
//	}



	/* Search coding */

	/*
	 * @GetMapping("/filterper") public String FilterStudentDetails(Model m, String
	 * keyword) { List<Students_Details> stinfo = stservice.getAllStudentsDetails();
	 * 
	 * if (keyword != null) { m.addAttribute("stinfo",
	 * stservice.findByKeyword(keyword)); } else { m.addAttribute("stinfo", stinfo);
	 * }
	 * 
	 * return "user/view_students_details"; }
	 */
	

	
	
	

	

	

}
