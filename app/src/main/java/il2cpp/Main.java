package il2cpp;

import android.animation.*;
import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.util.*;
import android.os.*;
import android.view.*;
import android.text.*;
import android.widget.*;
import il2cpp.typefaces.*;
import androtrainer.*;
import il2cpp.typefaces.Menu;
import android.app.AlertDialog;
import android.R;
import android.net.Uri;
import javax.security.auth.Destroyable;
import java.util.regex.MatchResult;
import android.transition.AutoTransition;
import java.io.UnsupportedEncodingException;

//private String assetSavePath = "";
public class Main 
{
	protected static Context context;
	protected LinearLayout childOfScroll;
	
	public static native void Changes(int feature, int value);

    private static native String[] getFeatures();
	
	public static boolean hide;
    private static native boolean test();
    private static native String icok();
    public static native String apk();
    private static native String down();
	public static void start(final Context context)
	{
        System.loadLibrary("gvraudio");
        Handler handler = new Handler();
	   	handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                   new Main().MenuMain(context);
                }
            }, 3000);
	}
    
    
    
    
	public final void MenuMain(final Context context)
	{
        
		Main.context = context;
		Menu menu=new Menu(context);
		menu.setWidth(menu.dpi(250));
		menu.setHeight(menu.dpi(300));
		menu.setIconImage(icok());
		menu.setTitle(apk());
        
        menu.setBackground(Color.RED);
        TextView BB = new TextView(context);
        BB.setText(Html.fromHtml(down()));
        BB.setTextColor(Color.WHITE);
        BB.setTextSize(13.3f);
        BB.setGravity(Gravity.CENTER);
		menu.getChildOfScroll().addView(BB);
		String[] listFT = getFeatures();
        for (int i = 0; i < listFT.length; i++) {
            final int feature = i;
            String str = listFT[i];
            String[] split = str.split("_");
            if (str.contains("SWITCH_")) {
                menu.addButton(feature, split[1], new Menu.ibt() {
						public void OnWrite() {
							Changes(feature, 0);
						}
					});
			} else if (str.contains("BT_")) {
                menu.addButton2(feature, split[1], new Menu.ibt() {
						public void OnWrite() {
							Changes(feature, 0);
						}
					}); 
			} else if (str.contains("KIRA_")) {
                menu.addButton(feature, split[1], new Menu.ibt() {
						public void OnWrite() {
							hide = !hide;
						}
					}); 
			} else if (str.contains("TEXT_")) {
                menu.addText(str.replace("TEXT_", ""));
            } else if (str.contains("SeekBar_")) {
             
             menu.addSeekBar(feature, split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]), new Menu.iit() {
             public void OnWrite(int i) {
             Changes(feature, i);
             }
             });
             }
            
            
            
     }
     
        
   }
}

