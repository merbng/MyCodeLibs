package com.app.merbng.mycodelibs.beans;

import android.text.TextUtils;
import android.util.SparseArray;

import com.app.merbng.mycodelibs.base.BaseBean;


/**
 * Created by ght on 2016/8/20.
 */
@SuppressWarnings("CheckStyle")
public class ErrorMessage extends BaseBean {
    private static final SparseArray<String> map = new SparseArray<>();

    static {
        map.put(1, "未知错误，请联系客服或管理员");
        map.put(0, "请求成功");
        map.put(-10000, "系统错误");
        map.put(-20000, "参数不正确");
        map.put(-30000, "权限验证失败，请重新登录");
        map.put(-40000, "该账号被限制登录");
        map.put(-10001, "手机号已经注册");
        map.put(-10002, "手机号未绑定");
        map.put(-10003, "验证码不正确");
        map.put(-10004, "昵称已被使用");
        map.put(-10005, "账号不存在");
        map.put(-10006, "密码不正确");
        map.put(-10007, "帖子类型名字已经存在");
        map.put(-10008, "帖子标题已经存在");
        map.put(-10009, "不是管理员不能进行操作");
        map.put(-10010, "对此文章没有修改权限");
        map.put(-10011, "对此评论或者回复没有修改权限");
        map.put(-10012, "已经关注过对方不需要再次关注");
        map.put(-10013, "没有关注对方不需要取消关注");
        map.put(-10014, "此帖子已经保存过不需要再次保存");
        map.put(-10015, "此帖子还没有保存不需要取消保存");
        map.put(-10016, "今天已经签过到了");
        map.put(-10017, "已经订阅过该帖子类别");
        map.put(-10018, "没有订阅过该帖子类别不需要取消订阅");
        map.put(-10019, "已经顶过或者踩过,不要做重复操作");
        map.put(-10020, "已经激活过了,不需要再次激活");
        map.put(-10021, "邀请码已经被使用过了");
        map.put(-10022, "未设置密码，请使用验证码登录");
        map.put(-10023, "该用户不存在");
        map.put(-10024, "邀请码不存在");
        map.put(-10025, "发帖间隔不足30秒");
        map.put(-10026, "已经读过该帖子");
        map.put(-10027, "存在未处理的申请记录，请等待处理后再申请");
        map.put(-10028, "当前用户非普通用户");
        map.put(-10029, "当前用户积分不足");
        map.put(-10030, "当前帖子不存在");
        map.put(-10031, "当标签名称不能为空");
        map.put(-10032, "当前标签已存在");
        map.put(-10033, "标签名称含有含有敏感词汇");
        map.put(-10034, "昵称含有含有敏感词汇");
        map.put(-10035, "没有顶踩过帖子不需要操作");
        map.put(-10036, "自己不能赞助自己");
        map.put(-10038, "您已经加入社区");
        map.put(-10039, "绑定账号不能加入/退出指定社区");
        map.put(-10040, "您被禁止加入社区");
        map.put(-10041, "权限不足不能查看该用户保存的帖子");
        map.put(-10042, "您尚未加入该社区");
        map.put(-10043, "该社区已经存在");
        map.put(-10044, "社区的管理员不能加入/退出指定的社区");
        map.put(-50000, "您的账户被禁言");

    }

    static String getErrorMessage(int code) {
        String result = map.get(code);
        if (TextUtils.isEmpty(result)) {
            result = map.get(1);
        }
        return result;
    }

}
