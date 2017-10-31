package com.example.jws.fairies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by 김요셉 on 2017-10-29.
 */

public class educationPage extends AppCompatActivity {
        private WebView webView2;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.education_page);

            webView2=(WebView)findViewById(R.id.webView2);

            webView2.getSettings().setJavaScriptEnabled(true);
            webView2.getSettings().setBuiltInZoomControls(true);
            webView2.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView2.getSettings().setSaveFormData(true);

            webView2.loadUrl("https://www.sejonghakdang.org/sjcustu/dms/video/list.do;jsessionid=M310NJ4ahjwRII81hUc0Roz5rUbp6a4GMe01acOKdWuaBAX3OWR1I67QOYkeB3en.was_servlet_engine17?currentPage=1&orderBy=w&viewNum=10&sch_use_branch_cls_nm=&sch_educ_branch_cls_nm=발음&data_formlity_cls_nm=&data_topic_cls_nm=&dms_searchTerm=&sch_use_branch_cls_cd_dummy=&sch_educ_branch_cls_cd_dummy=&data_formlity_cls_cd_dummy=&data_topic_cls_cd_dummy=&reSearch=&old_searchTerm=&searchWhereset=%40where+%7B+SEARCHALL(HASALL%7C%27all%27%7C0)+%7D&subj_idx=&subj_nm=&subj_select_val_1=null&subj_select_val_2=null&searchAll=동영상&searchAll_category=자료유형&searchAll_2nd=&searchAll_2nd_category=&currentMenuId=&sch_subj_cls_idx1=&input_keyword_sch_option=");
        }
}
