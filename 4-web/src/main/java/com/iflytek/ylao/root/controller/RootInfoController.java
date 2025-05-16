package com.iflytek.ylao.root.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iflytek.ylao.common.loginpojo.RegisterPojo;
import com.iflytek.ylao.common.tool.Tool;
import com.iflytek.ylao.volunteer.pojo.VolunteerInfo;
import com.iflytek.ylao.volunteer.service.VolunteerInfoService;

import com.iflytek.ylao.common.loginpojo.LoginPojo;
import com.iflytek.ylao.common.result.AjaxResult;

import com.iflytek.ylao.missinfo.pojo.MissInfo;
import com.iflytek.ylao.missinfo.service.MissInfoService;

import com.iflytek.ylao.root.pojo.RootInfo;
import com.iflytek.ylao.root.service.RootInfoService;

import com.iflytek.ylao.usr.pojo.UsrInfo;
import com.iflytek.ylao.usr.service.UsrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static com.iflytek.ylao.common.result.AjaxResult.*;

@SuppressWarnings("all")
@RestController
@RequestMapping("/root")
public class RootInfoController {

    @Autowired
    private RootInfoService rootInfoService;

    @Autowired
    private VolunteerInfoService volunteerInfoService;

    @Autowired
    private UsrInfoService usrInfoService;

    @Autowired
    private MissInfoService missInfoService;


    private boolean login;
    /** 登录验证 */
    @RequestMapping("/loginIn")
    public AjaxResult loginIn(HttpServletRequest request, HttpSession session,
                              String username,
                              LoginPojo loginPojo,
                              RootInfo rootInfo,
                              VolunteerInfo volunteerInfo,
                              UsrInfo usrInfo){

//        if (loginPojo.getCaptcha().equalsIgnoreCase(String.valueOf(request.getSession().getAttribute("captCode")))) {
//
//        }
//            return AjaxResult.error("验证码错误");

        //超管登录
        if (0 == loginPojo.getPower()){
            rootInfo.setName(loginPojo.getUsername());
            rootInfo.setPwd(loginPojo.getPassword());
            RootInfo root = rootInfoService.sltName(rootInfo);
            if (root == null){
                return AjaxResult.error("登录名不存在");
            }
            login = rootInfoService.loginIn(rootInfo);
            if (login) {
                session.setAttribute("username",root.getNickName());
                session.setAttribute("power",root.getPower());
                session.setAttribute("root",root);
                return AjaxResult.success(0,"登录成功");
            } else return AjaxResult.error("登录名或密码错误");
        }

        //志愿者登录
        if (1 == loginPojo.getPower()){
            volunteerInfo.setVolunteerLogin(loginPojo.getUsername());
            volunteerInfo.setVolunteerPwd(loginPojo.getPassword());
            VolunteerInfo volunteer = volunteerInfoService.sltName(volunteerInfo);
            if (volunteer == null){
                return AjaxResult.error("登录名不存在");
            }
            login = volunteerInfoService.loginIn(volunteerInfo);
            if (login) {
                session.setAttribute("username",volunteer.getVolunteerName());
                session.setAttribute("power",volunteer.getPower());
                session.setAttribute("volunteer",volunteer);
                return AjaxResult.success(1,"登录成功");
            } else return AjaxResult.error("登录名或密码错误");
        }

        //用户登录
        if (2 == loginPojo.getPower()){
            usrInfo.setUsrLogin(loginPojo.getUsername());
            usrInfo.setUsrPwd(loginPojo.getPassword());
            UsrInfo usr = usrInfoService.sltName(usrInfo);
            if (usr == null){
                return AjaxResult.error("登录名不存在");
            }
            login = usrInfoService.loginIn(usrInfo);
            if (login) {

                session.setAttribute("username",usr.getUsrName());
                session.setAttribute("power",usr.getUsrPwd());
                session.setAttribute("usr",usr);
                //                    session.setAttribute("older",older);
                return AjaxResult.success(2,"登录成功");
            } else return AjaxResult.error("登录名或密码错误");
        }

        return AjaxResult.success("");
    }

    /** 用户注册 **/
    @RequestMapping("/register")
    public AjaxResult register(HttpServletRequest request, HttpSession session, RegisterPojo registerPojo) {
        if (1 == registerPojo.getPower()) {
            // 志愿者注册
            VolunteerInfo volunteer = new VolunteerInfo();
            volunteer.setVolunteerLogin(registerPojo.getLogin());
            volunteer.setVolunteerPwd(registerPojo.getPassword());
            volunteer.setVolunteerSex(registerPojo.getSex());
            volunteer.setVolunteerTel(registerPojo.getTel());

            volunteer.setVolunteerName(registerPojo.getUsername());
            VolunteerInfo volunteerInfo = volunteerInfoService.sltName(volunteer);
            if (volunteerInfo != null){
                return error("添加失败,登录名已被注册");
            }
            int insert = volunteerInfoService.insert(volunteer);
            if (1 == insert){
                session.setAttribute("username",volunteer.getVolunteerName());
                session.setAttribute("power",volunteer.getPower());
                session.setAttribute("volunteer",volunteer);
                return AjaxResult.success(1,"添加成功");
            }
            return error("添加失败");
        } else if (2 == registerPojo.getPower()) {
            // 用户注册
            UsrInfo usr = new UsrInfo();
            usr.setUsrLogin(registerPojo.getUsername());
            usr.setUsrPwd(registerPojo.getPassword());
            usr.setUsrName(registerPojo.getUsername());
            usr.setUsrTel(registerPojo.getTel());

            UsrInfo results = usrInfoService.sltName(usr);
            if (results != null){
                return error("添加失败,登录名已被注册");
            }
            int insert = usrInfoService.insert(usr);
            if (1 == insert){
                session.setAttribute("username",usr.getUsrName());
                session.setAttribute("power",usr.getUsrPwd());
                session.setAttribute("usr",usr);
                return AjaxResult.success(2,"添加成功");
            }
            return error("添加失败");
        }
        // 如果不是志愿者或用户类型，则返回错误信息
        return AjaxResult.error("不支持此用户类型注册");
    }


    /** admin  ---  List */
    @RequestMapping("/adminList")
    public AjaxResult adminList(@RequestParam(defaultValue = "1" , value = "page") Integer pageNum,
                                @RequestParam(defaultValue = "10", value = "limit") Integer pageSize,
                                @RequestParam(defaultValue = ""  , value = "volunteerName") String user
                                ){
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum,pageSize);
        if (user == null || user.equals("")) {
            map.put("user","");
            List<VolunteerInfo> admins = volunteerInfoService.listAll(map);
            PageInfo<VolunteerInfo> page = new PageInfo<>(admins);
            return successData(page.getTotal(), admins);
        }
            map.put("user",user);
            List<VolunteerInfo> admins = volunteerInfoService.listAll(map);
            PageInfo<VolunteerInfo> page = new PageInfo<>(admins);
            return successData(page.getTotal(), admins);
    }


    /** usr --- List */
    @RequestMapping("/userList")
    public AjaxResult userList(@RequestParam(defaultValue = "1", value = "page") Integer pageNum,
                               @RequestParam(defaultValue = "10", value = "limit") Integer pageSize,
                               @RequestParam(defaultValue = "" , value = "usrName") String usr
    ){
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum,pageSize);
        if (usr == null || usr.equals("")) {
            map.put("usr","");
            List<UsrInfo> usrInfos = usrInfoService.listAll(map);
            PageInfo<UsrInfo> page = new PageInfo<>(usrInfos);
            return successData(page.getTotal(), usrInfos);
        }
        map.put("usr",usr);
        List<UsrInfo> usrInfos = usrInfoService.listAll(map);
        PageInfo<UsrInfo> page = new PageInfo<>(usrInfos);
        return successData(page.getTotal(), usrInfos);
    }

    /** 寻亲 */
    @RequestMapping("/missinfo")
    public AjaxResult missinfo(@RequestParam(defaultValue = "1",  value = "page") Integer pageNum,
                             @RequestParam(defaultValue = "10", value = "limit") Integer pageSize,
                             @RequestParam(defaultValue = ""  , value = "id") String id
    ){
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum,pageSize);
        if (id != null && !id.equals("")) {
            map.put("id",id);

        }
        List<MissInfo> missinfo = missInfoService.listAll(map);
        PageInfo<MissInfo> page = new PageInfo<>(missinfo);
        return successData(page.getTotal(), missinfo);
    }


    //==============添加功能=================

    /** 添加志愿者 */
    @RequestMapping("/addVolunteer")
    public AjaxResult addVolunteer(VolunteerInfo volunteerInfo){
        System.out.println(volunteerInfo);
        volunteerInfo.setVolunteerId((int) ((Math.random()*9+1)*10));
        int insert = volunteerInfoService.insert(volunteerInfo);
        if (1 == insert){
            return success("添加成功");
        }
        return error("添加失败");
    }

    /** 添加用户 */
    @RequestMapping("/addUsr")
    public AjaxResult addUsr(UsrInfo usrInfo){
        UsrInfo results = usrInfoService.sltName(usrInfo);
        if (results != null){
            return error("添加失败,登录名已被注册");
        }
        int insert = usrInfoService.insert(usrInfo);
        if (1 == insert){
            return success("添加成功");
        }
        return error("添加失败");
    }

    @RequestMapping("/addMissinfo")
    public AjaxResult addMissinfo(@RequestBody MissInfo missinfo){
        missinfo.setBirthday(missinfo.getBirthday());
        missinfo.setMisstime(missinfo.getMisstime());
        missinfo.setId((int) ((Math.random()*9+1)*100000));
        int insert = missInfoService.insert(missinfo);
        if (1 == insert){
            return success("添加成功");
        }
        return error("添加失败");
    }



    //==============修改功能=================

    /** 修改管理员 */
    @RequestMapping("/modifyVolunteer")
    public AjaxResult modifyVolunteer(VolunteerInfo volunteerInfo){
        int update = volunteerInfoService.update(volunteerInfo);
        if (1 == update){
            return success("修改成功");
        }
        return error("修改失败");
    }

    @RequestMapping("/modifyUsr")
    public AjaxResult modifyUsr(UsrInfo usrInfo){
        int update = usrInfoService.update(usrInfo);
        if (1 == update){
            return success("修改成功");
        }
        return error("修改失败");
    }

    @RequestMapping("/modifyMissinfo")
    public AjaxResult modifyMissinfo(MissInfo missinfo){
        System.out.println(missinfo);
        int update = missInfoService.update(missinfo);
        if (1 == update){
            return success("修改成功");
        }
        return error("修改失败");
    }

    //==============删除功能=================

    /** 删除管理员 */
    @RequestMapping("/delVolunteerInfo")
    public AjaxResult delvolunteerInfo(Integer volunteerId){
        volunteerInfoService.delVolunteer(volunteerId);
        return success("删除成功");
    }
    /** 批量删除管理员 */
    @RequestMapping("/batchDelAdmin")
    public AjaxResult batchDel(String listStr){
        if (null != listStr && !"".equals(listStr)){
            String[] ids = listStr.split(",");
            for (String id:ids) {
                if (null != id && !"".equals(id)) {
                    System.out.println(id);
                    volunteerInfoService.delVolunteer(Integer.valueOf(id));
                }
            }
        }
        return success("删除成功");
    }

    /** 删除用户 */
    @RequestMapping("/delUsr")
    public AjaxResult delUsr(Integer usrId){
        usrInfoService.delete(usrId);
        return success("删除成功");
    }
    /** 批量删除用户 */
    @RequestMapping("/batchDelUsr")
    public AjaxResult batchDelUsr(String listStr){
        if (null != listStr && !"".equals(listStr)){
            String[] ids = listStr.split(",");
            for (String id:ids) {
                if (null != id && !"".equals(id)) {
                    System.out.println(id);
                    usrInfoService.delete(Integer.valueOf(id));
                }
            }
        }
        return success("删除成功");
    }

    /** 删除寻亲 **/
    @RequestMapping("/delMissinfo")
    public AjaxResult delMissinfo(Integer id){
        missInfoService.delete(id);
        return success("删除成功");
    }

    @RequestMapping("/batchDelMissinfo")
    public AjaxResult batchDelMissinfo(String listStr){
        if (null != listStr && !"".equals(listStr)){
            String[] ids = listStr.split(",");
            for (String id:ids) {
                if (null != id && !"".equals(id)) {
                    System.out.println(id);
                    missInfoService.delete(Integer.valueOf(id));
                }
            }
        }
        return success("删除成功");
    }
}
