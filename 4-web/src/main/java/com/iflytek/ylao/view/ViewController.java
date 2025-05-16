package com.iflytek.ylao.view;

import com.iflytek.ylao.volunteer.pojo.VolunteerInfo;
import com.iflytek.ylao.volunteer.service.VolunteerInfoService;
import com.iflytek.ylao.missinfo.pojo.MissInfo;
import com.iflytek.ylao.missinfo.service.MissInfoService;
import com.iflytek.ylao.usr.pojo.UsrInfo;
import com.iflytek.ylao.usr.service.UsrInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 视图跳转
 */

@Controller
@RequestMapping("/view")
public class ViewController {
    @Autowired
    private VolunteerInfoService volunteerInfoService;

    @Autowired
    private UsrInfoService usrInfoService;

    @Autowired
    private MissInfoService missinfoService;

    /**
     * 首页跳转
     **/
    @GetMapping("/welcome")
    public String welcome() {
        return "page/welcome";
    }


    /**
     * admin --- list
     */
    @GetMapping("/adminList")
    public String adminList() {
        return "page/volunteerList";
    }

    @GetMapping("/usrList")
    public String usrList() {
        return "page/usrList";
    }

    @GetMapping("/missinfo")
    public String missinfo() {
        return "page/missinfo";
    }


    //===============跳转模块： 添加功能===================

    /**
     * 添 加 管 理 员
     */
    @GetMapping("/addAdmin")
    public String addAdmin() {
        return "page/add/addAdmin";
    }

    /**
     * 添 加 用 户
     */
    @GetMapping("/addUsr")
    public String addUsr() {
        return "page/add/addUsr";
    }

    @GetMapping("/addMissinfo")
    public String addMissinfo() {
        return "page/add/addMissinfo";
    }
    @GetMapping("/addVolunteer")
    public String addVolunteer() {
        return "page/add/addVolunteer";
    }


    //================编辑功能==================

    /**
     * 修 改 管 理 员
     */
    @GetMapping("/modifyAdmin")
    public String modifyAdmin(@RequestParam(value = "adminId") long adminId, Model model) {
        VolunteerInfo volunteerInfo = volunteerInfoService.getById(adminId);
        model.addAttribute("volunteer", volunteerInfo);
        return "page/modify/modifyAdmin";
    }

    @GetMapping("/modifyUsr")
    public String modifyUsr(@RequestParam(value = "usrId") long usrId, Model model) {
        UsrInfo usrInfo = usrInfoService.getById(usrId);
        model.addAttribute("usrInfo", usrInfo);
        return "page/modify/modifyUsr";
    }


    @GetMapping("/modifyMissinfo")
    public String modifyMissinfo(@RequestParam(value = "id") long id, Model model) {
        MissInfo missinfo = missinfoService.getById(id);
        model.addAttribute("missinfo", missinfo);
        return "page/modify/modifyMissinfo";
    }
    @GetMapping("/modifyVolunteer")
    public String modifyVolunteer(@RequestParam(value = "volunteerId") long volunteerid, Model model) {
        VolunteerInfo volunteerInfo = volunteerInfoService.getById(volunteerid);
        model.addAttribute("volunteerInfo", volunteerInfo);
        return "page/modify/modifyVolunteer";
    }

//    前台视图跳转

    @GetMapping("/project/index")
    public String case01Index(){
        return "/project/index";
    }

    @GetMapping("/front/help")
    public String help(){
        return "/front/help";
    }

    @GetMapping("/front/familySearch")
    public String familySearch(){
        return "/front/familySearch";
    }

    @GetMapping("/front/volunteerRegister")
    public String volunteerRegister(){
        return "/front/volunteerRegister";
    }

    @GetMapping("/front/thank")
    public String thank(){
        return "/front/thank";
    }

}
