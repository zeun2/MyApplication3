package com.example.gangjieun.myapplication3;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnPopup;
    private Button btnClosePopup;
    //창모드
    private PopupWindow popupWindow;
    private int mWidthPixels, mHeightPixels = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //윈도우 매니저 객체만들기
        WindowManager w = getWindowManager();
        //display 객체는 윈도우 객체에서 get해옴
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);

        //단말기의 해상도를 체크하는 부분 -> 여기를 참고하여 팝업윈도우 크기를 조정
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;

        btnPopup = (Button)findViewById(R.id.btnPopup);
        //new로 리스너만들지않고 다른방법
        btnPopup.setOnClickListener(popupListner);

    }

    private View.OnClickListener popupListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            iniPopupWindow();
        }
    };

    // ini는 리턴값이 없다.
    private void iniPopupWindow()
    {
        try{
            //이 액티비티안에 있는 나를 참조하여 인플레이터 객체를 만들겠다.
            LayoutInflater inflater = (LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //뷰객체 -> 어떤 이름의 xml을 호출할 것인가, 레이아웃으로 묶은 애들을 viewGroup으로 형변환하여 갖고온다.
            View layout = inflater.inflate(R.layout.popup, (ViewGroup)findViewById(R.id.popup_element));

            //팝업윈도우를 띄우고 거기안에 띄울 view 정의하고 크기를 정해준다. 포커서블은 팝업윈도우에 포커스를 줄 것인가 말것인가.
            popupWindow = new PopupWindow(layout,mWidthPixels -100 ,mHeightPixels -500,true);
            //팝업윈도우 위치설정
            popupWindow.showAtLocation(layout, Gravity.CENTER, 0,0);

            btnClosePopup = (Button)layout.findViewById(R.id.btnClosePopup);
            btnClosePopup.setOnClickListener(disListener);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private View.OnClickListener disListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            popupWindow.dismiss();
        }
    };


    /*
    public void OnClick(View view){
        switch (view.getId())
        {
            case R.id.btnPopup:
            case R.id.xxxx:
                method1();
                break;

        }
    }
    */
}
