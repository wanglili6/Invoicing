package com.mtecc.mmp.invoicing.activity.comodity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtecc.mmp.invoicing.R;
import com.mtecc.mmp.invoicing.activity.SaoMaActivity;
import com.mtecc.mmp.invoicing.activity.comodity.adapter.CommodityExListAdapter;
import com.mtecc.mmp.invoicing.activity.comodity.bean.CommodityBean;
import com.mtecc.mmp.invoicing.base.BaseActivity;
import com.mtecc.mmp.invoicing.utils.ShowDalogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品列表
 */
public class CommodityListActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_left_select)
    TextView tvLeftSelect;
    @BindView(R.id.img_left_select)
    ImageButton imgLeftSelect;
    @BindView(R.id.rl_right_left)
    RelativeLayout rlRightLeft;
    @BindView(R.id.tv_select)
    TextView tvSelect;
    @BindView(R.id.img_select)
    ImageButton imgSelect;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.rl_title_bg)
    RelativeLayout rlTitleBg;
    @BindView(R.id.iv_bar_search)
    RelativeLayout ivBarSearch;
    @BindView(R.id.et_serch)
    EditText etSerch;
    @BindView(R.id.iv_serch)
    ImageView ivSerch;
    @BindView(R.id.rl_serch_bar)
    LinearLayout rlSerchBar;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.img_commodity_list_saoma)
    ImageView imgCommodityListSaoma;
    @BindView(R.id.commodity_list_tv_default)
    TextView commodityListTvDefault;
    @BindView(R.id.commodity_list_tv_detop)
    TextView commodityListTvDetop;
    @BindView(R.id.commodity_list_tv_price)
    TextView commodityListTvPrice;
    @BindView(R.id.commodity_list_recycle_view)
    ExpandableListView commodityListRecycleView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initParms(Bundle parms) {
        ivBack.setVisibility(View.VISIBLE);
        imgSelect.setVisibility(View.VISIBLE);
        imgLeftSelect.setVisibility(View.VISIBLE);
        tvTitle.setText("商品列表");
        imgSelect.setBackgroundResource(R.mipmap.add_select);
        imgLeftSelect.setBackgroundResource(R.mipmap.filter);
        List<CommodityBean> mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<String> nameList = new ArrayList<>();
            CommodityBean commodityBean = new CommodityBean();
            commodityBean.setTitle("分类" + i);
            for (int j = 0; j < 5; j++) {
                nameList.add("商品" + i + j);
            }
            commodityBean.setNameList(nameList);
            mList.add(commodityBean);
        }
        CommodityExListAdapter adapter = new CommodityExListAdapter(this, mList);
        commodityListRecycleView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //默认展开
        for (int i = 0; i < mList.size(); i++) {
            commodityListRecycleView.expandGroup(i);
        }
        //设置点击不回缩
        commodityListRecycleView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;
            }
        });
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_commodity_list;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
        //设置recycleView的布局管理器
//        commodityListRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @OnClick({R.id.rl_back, R.id.img_select, R.id.img_left_select, R.id.iv_bar_search, R.id.iv_serch, R.id.tv_search, R.id.img_commodity_list_saoma})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_bar_search:
                rlSerchBar.setVisibility(View.VISIBLE);
                ivBarSearch.setVisibility(View.GONE);
                break;
            case R.id.iv_serch:
                rlSerchBar.setVisibility(View.GONE);
                ivBarSearch.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_search:
                break;
            case R.id.img_commodity_list_saoma:
                Intent intent = new Intent();
                intent.setClass(this, SaoMaActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.img_left_select:
                //筛选
                View customizeDialogView = ShowDalogUtils.showCustomizeDialog(this, R.layout.commodity_shaixuan_dialog);
                AlertDialog dialog = ShowDalogUtils.showDialog(this, false, customizeDialogView);
                dialogClick(customizeDialogView, dialog);
                break;
            case R.id.img_select:
                //增加商品
                break;

        }
    }

    @Override
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        super.startActivityForResult(cls, bundle, requestCode);
    }

    /**
     * 对话框的初始化以及点击事件
     * @param customizeDialogView
     * @param dialog
     */
    private void dialogClick(View customizeDialogView, final AlertDialog dialog) {
        TextView tvSure = customizeDialogView.findViewById(R.id.tv_sure);
        TextView tvDiss = customizeDialogView.findViewById(R.id.tv_diss);
        final EditText commodityDialogEtName = customizeDialogView.findViewById(R.id.commodity_dialog_et_name);
        final EditText commodityDialogEtType = customizeDialogView.findViewById(R.id.commodity_dialog_et_type);
        final EditText commodityDialogEtguige = customizeDialogView.findViewById(R.id.commodity_dialog_et_guige);
        final EditText commodityDialogEtpinpai = customizeDialogView.findViewById(R.id.commodity_dialog_et_pinpai);
        tvDiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                String name = commodityDialogEtName.getText().toString().trim();
                String type = commodityDialogEtType.getText().toString().trim();
                String guige = commodityDialogEtguige.getText().toString().trim();
                String pinpai = commodityDialogEtpinpai.getText().toString().trim();
                //TODO:筛选请求

            }
        });
    }


}
