package com.mtecc.mmp.invoicing.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.login_user_name)
    EditText loginUserName;
    @BindView(R.id.login_user_pwd)
    EditText loginUserPwd;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    public int[] twoSum(int[] nums, int target) {
        int[] ints = null;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (j + 1 <= nums.length - 1) {
                    if (i == j) {
                        if (nums[i] + nums[j + 1] == target) {
                            ints = new int[]{i, j + 1};
                            return ints;
                        }
                    } else {
                        if (nums[i] + nums[j] == target) {
                            ints = new int[]{i, j};
                            return ints;
                        }
                    }
                }

            }
        }
        return ints;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;//进位
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }


        return dummyHead.next;

    }

//    public int lengthOfLongestSubstring(String s) {
//        Map<String, Integer> map = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            for (int j = 1; j < s.length(); j++) {
//                if (i == j || i > j) {
//                    if (i + 1 < s.length()) {
//                        j = i + 1;
//                        String maxStr = s;
//                        String substring = s.substring(i, j);
//                        LogUtils.i(substring + "-===->>>>>");
////                定义一个统计变量
//                        int count = 0;
//                        //在大串中查找小串是否存在，用 int indexOf(String str):返回指定字符在此字符串中第一次出现处的索引。
//                        while ((maxStr.indexOf(substring)) != -1) {
//                            //当得到的索引不是-1时
//                            count++;
//                            //从得到的索引开始，再加上小串的长度，到字符串的最后，开始截取一个新的字符串，再把这个字符串赋值给大串，替换之前的大串
//                            maxStr = maxStr.substring(maxStr.indexOf(substring) + substring.length());
//                            LogUtils.i(maxStr + "-===->>>>>");
//                        }
//                        LogUtils.i(count + "-===->>>>>");
//                        map.put(substring, count);
//                    }
//
//                } else {
//                    String maxStr = s;
//                    String substring = s.substring(i, j);
//                    LogUtils.i(substring + "-===->>>>>");
////                定义一个统计变量
//                    int count = 0;
//                    int index;
//                    //在大串中查找小串是否存在，用 int indexOf(String str):返回指定字符在此字符串中第一次出现处的索引。
//                    while ((index = maxStr.indexOf(substring)) != -1) {
//                        //当得到的索引不是-1时
//                        LogUtils.i(index + "-===->>>>>");
//                        count++;
//                        //从得到的索引开始，再加上小串的长度，到字符串的最后，开始截取一个新的字符串，再把这个字符串赋值给大串，替换之前的大串
//                        maxStr = maxStr.substring(maxStr.indexOf(substring) + substring.length());
//                        LogUtils.i(maxStr + "-===->>>>>");
//                    }
//                    LogUtils.i(count + "-===->>>>>");
//                    map.put(substring, count);
//                }
//
//            }
//        }
//        Iterator<String> iterator = map.keySet().iterator();
//        List<String> nextStr = new ArrayList<>();
//        while (iterator.hasNext()) {
//            String next = iterator.next();
//            LogUtils.d(next + "---->>>>" + map.get(next));
//            nextStr.add(next);
//        }
//        String  maxNum= 0;
//        for (int i = 0; i < nextStr.size(); i++) {
//
//            if (nextStr.get(i).length()<nextStr.get(i+1).length()){
//                maxNum=nextStr.get(i);
//
//                String s2 = nextStr.get(i + 1);
//                s2=nextStr.get(i);
//                nextStr.get(i)=maxNum;
//
//            }
//        }
//        return 0;
//    }

    @OnClick(R.id.tv_login)
    public void onViewClicked() {
        int[] ints = twoSum(new int[]{2, 5, 5, 11}, 10);
        for (int i = 0; i < ints.length; i++) {
            LogUtils.d(ints[i] + "====");

        }

        ListNode listNode = new ListNode(2);
        listNode.next = new ListNode(4);
        listNode.next.next = new ListNode(3);
        ListNode listNode1 = new ListNode(5);
        listNode1.next = new ListNode(6);
        listNode1.next.next = new ListNode(4);
        ListNode listNode2 = addTwoNumbers(listNode, listNode1);
        LogUtils.d(listNode2.val + "====---" + listNode2.next.val + "---" + listNode2.next.next.val);
        String userName = loginUserName.getText().toString().trim();
        String userPwd = loginUserPwd.getText().toString().trim();


//        lengthOfLongestSubstring("abcabcbb");


        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPwd)) {
            showToast("用户名或密码不能为空!");
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }


    }

}
