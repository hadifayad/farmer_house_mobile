package com.uzarsif;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class WhoActivity extends AppCompatActivity {
    Button  ta3reef,amal,bayt,mazroat,torok;
    String ta3reefString , amalString , baytString,mazroatString,torokString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#81bb28"));
        ta3reef = findViewById(R.id.ta3reef);
        amal = findViewById(R.id.amal);
        bayt = findViewById(R.id.bayt);
        mazroat = findViewById(R.id.mazroat);
        torok = findViewById(R.id.torok);
        ta3reefString ="يأتي دور جمعية يوزرسيف كمساهم في بناء الاقتصاد الوطني من بوابة الانتاج الزراعي  ، وإحلال الزراعة المحلية ومنتجاتها كبديل للإستيراد من الخارج .\n" +
                "   أخذت الجمعية على عاتقها السعي إلى   تطوير الإنتاج الزراعي وبناء الثقة بجدوى الزراعة باستقطاب عدد اكبر من المزارعين الجدد ، تثبيت السابقين ، زيادة الأراضي المزروعة ، وذلك  من خلال جملة من الخدمات الاساسية، واقعية و الكترونية ، مواكبة المزارعين ، تقليل معوقات العملية الزراعية ، وصولا اللى تأمين تصريف عادل لكلا طرفي الانتاج الزراعي (مزارعين وموردين ).الجمعية تأخذ بعين الاعتبار كل الصعوبات المحتملة لتحقيق تواصل مستدام مع المزارع و تعتبر الموانع  تحديات سيتم اجتيازها لتحقيق الرؤية : \n" +
                "إنتاج زراعي مجدٍ و متقّدم";
                amalString ="يواكب مندوبو الجمعية مساعي التنمية وتطوير القطاع الزراعي ، والتواصل المباشر مع المزارعين في اطار تقديم الدعم اليومي ، والاستفادة من التطبيق الإلكتروني . \n";
        baytString =" مركز استلام الغلال وغربلتها ومعرض مدخلات العملية الزراعية\n" +
                " ( بذور ، أسمدة ، مبيدات ، أدوات ري ، آلات فلاحة وحصاد ...)\n";
        mazroatString=" حبوب وبقوليات ، نجيليات ، زراعات زيتية ، زراعات علفية ، أشجار مثمرة ، زراعات تخصصية .\n";
        torokString="شراء محاصيل المزارعين عبر عقود ، ضمان ألأراضي وزراعتها  ، بيع المدخلات بأسعار تشجيعية ، توفير خدمات إرشادية توجيهية ، إختيار تقنيات حديثة .\n";
        ta3reef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DisplayDialog cdd = new DisplayDialog(WhoActivity.this ,ta3reefString);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();


            }
        });
        amal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DisplayDialog cdd = new DisplayDialog(WhoActivity.this ,amalString);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();


            }
        });
        bayt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DisplayDialog cdd = new DisplayDialog(WhoActivity.this ,baytString);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();


            }
        });
        mazroat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DisplayDialog cdd = new DisplayDialog(WhoActivity.this ,mazroatString);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();


            }
        });
        torok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DisplayDialog cdd = new DisplayDialog(WhoActivity.this ,torokString);
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();


            }
        });


    }
}