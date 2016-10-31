package com.app.merbng.mycodelibs.activitys;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.merbng.mycodelibs.R;
import com.app.merbng.mycodelibs.base.BaseActivity;

/**
 * 
 * 图文环绕
 * http://blog.sina.com.cn/s/blog_47021dd401012szb.html
 * http://blog.csdn.net/freesonhp/article/details/10200377
 * https://github.com/hongyangAndroid/MixtureTextView
 */
public class ImgTextSoundActivity extends BaseActivity {
    boolean imageMeasured = false;
    TextView tv_right;
    TextView tv_bottom;

    static final String text = "叶凡：小说主角，与众老同学在泰山聚会时一同被九龙拉棺带离地球，" +
            "进入北斗星域，得知自己是荒古圣叶凡 叶凡体。历险禁地，习得源术，斗圣地世家，战太古生物，" +
            "重组天庭，叶凡辗转四方得到许多际遇和挑战，功力激增，眼界也渐渐开阔。一个浩大的仙侠世界，" +
            "就以他的视角在读者面前展开。姬紫月：姬家小姐，出场年龄十七岁。被叶凡劫持一同经历青铜古殿历险，" +
            "依靠碎裂的神光遁符解除禁制，反过来挟持叶凡一同进入太玄派寻找秘术。" +
            "在叶凡逃离太玄后姬紫月在孔雀王之乱中被华云飞追杀，又与叶凡[2]相遇，被叶凡护送回姬家" +
            "，渐渐对叶凡产生微妙感情。后成为叶凡的妻子，千载后于飞仙星成仙，在叶凡也进入仙路后再见庞博：" +
            "叶凡大学时最好的朋友，壮硕魁伟，直率义气。到达北斗星域后因服用了圣果被灵墟洞天作为仙苗，" +
            "在青帝坟墓处为青帝十九代孙附体离去，肉身被锤炼至四极境界。后叶凡与黑皇镇压老妖神识，" +
            "庞博重新掌控自己身躯，取得妖帝古经和老妖本体祭炼成的青莲法宝，习得妖帝九斩和天妖八式，" +
            "但仍伪装成老妖留在妖族。出关后找上叶凡，多次与他共进退。星空古路开启后由此离开北斗，" +
            "被叶凡从妖皇墓中救出，得叶凡授予者字秘、一气化三清，与叶凡同闯试炼古路，一起建设天庭";
    // 屏幕的高度  
    int screenWidth = 0;
    // 总共可以放多少个字  
    int count = 0;
    // textView全部字符的宽度  
    float textTotalWidth = 0.0f;
    // textView一个字的宽度  
    float textWidth = 0.0f;
    Paint paint = new Paint();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_text_sound);

        tv_right = (TextView) findViewById(R.id.test_tv_right);
        tv_bottom = (TextView) findViewById(R.id.test_tv_bottom);
        final ImageView imageView = (ImageView) findViewById(R.id.test_image);
        imageView.setImageResource(R.drawable.github);

        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        /**
         * 获取一个字的宽度 
         */
        textWidth = tv_right.getTextSize();
        paint.setTextSize(textWidth);

        /**
         * 因为图片一开始的时候，高度是测量不出来的，通过增加一个监听器，即可获取其图片的高度和长度 
         */
        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                if (!imageMeasured) {
                    imageMeasured = true;
                    int height = imageView.getMeasuredHeight();
                    int width = imageView.getMeasuredWidth();
                    drawImageViewDone(width, height);
                }
                return imageMeasured;
            }
        });
    }
    private void drawImageViewDone(int width, int height) {
        // 一行字体的高度  
        int lineHeight = tv_right.getLineHeight();
        // 可以放多少行  
        int lineCount = (int) Math.ceil((double) height / (double) lineHeight);
        // 一行的宽度  
        float rowWidth = screenWidth - width - tv_right.getPaddingLeft() - tv_right.getPaddingRight();
        // 一行可以放多少个字  
        int columnCount = (int) (rowWidth / textWidth);

        // 总共字体数等于 行数*每行个数  
        count = lineCount * columnCount;
        // 一个TextView中所有字符串的宽度和（字体数*每个字的宽度）  
        textTotalWidth = (float) ((float) count * textWidth);

        measureText();
        tv_right.setText(text.substring(0, count));

        // 检查行数是否大于设定的行数，如果大于的话，就每次减少一个字符，重新计算行数与设定的一致  
        while (tv_right.getLineCount() > lineCount) {
            count -= 1;
            tv_right.setText(text.substring(0, count));
        }
        tv_bottom.setPadding(0, lineCount * lineHeight - height, 0, 0);
        tv_bottom.setText(text.substring(count));
    }
    /**
     * 测量已经填充的长度，计算其剩下的长度 
     */
    private void measureText() {
        String string = text.substring(0, count);
        float size = paint.measureText(string);
        int remainCount = (int) ((textTotalWidth - size) / textWidth);
        if (remainCount > 0) {
            count += remainCount;
            measureText();
        }
    }
}
