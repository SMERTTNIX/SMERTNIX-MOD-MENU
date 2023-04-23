package il2cpp.typefaces;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.view.inputmethod.InputMethodManager;
import il2cpp.*;
import androtrainer.*;
import android.text.*;
import android.view.Window.*; 
import android.text.method.*;
import android.graphics.Typeface;
import android.util.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.Objects;

//native void setTitleText(TextView textView);
//native void setHeadingText(TextView textView);

public class Menu
{
	protected int WIDTH,HEIGHT;
    private native String gettype();
   /* public Typeface google(Context yes) {
	Typeface bruuh = Typeface.createFromAsset(yes.getAssets(), gettype());
	return bruuh; 
	}*/
	protected Context context;
	protected boolean isIconVisible;
	protected boolean isMenuVisible;
	protected ImageView iconView,closeView;
	protected FrameLayout parentBox;
	protected LinearLayout menulayout;  
	protected ScrollView scrollItems;    
	
	private native String SliderString(int feature, int value);

	protected TextView cred;

	protected WindowManager wmManager;
	protected WindowManager.LayoutParams wmParams;
	protected LinearLayout headerLayout;

	protected LinearLayout childOfScroll;
	protected LinearLayout infos;
    public static boolean game;

	public void addspace(int р0) {
	}

	public void setBackground(int rED) {
	}
	
	public TextView addText(String str) {
        TextView textView = new TextView(context);
        textView.setText(Html.fromHtml("<u><b>" + str + "</b></u>"));
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(12.3f);
		//textView.setTypeface(google(context));
        textView.setGravity(3);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        textView.setPadding(20, 0, 0, 0);
        getChildOfScroll().addView(textView);
        return textView;
    }
	
	public void addSeekBar(final int featurenum, final String feature, final int prog, int max, final iit interInt) {
			LinearLayout linearLayout = new LinearLayout(context);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
			linearLayout.setPadding(10, 5, 0, 5);
			linearLayout.setOrientation(LinearLayout.VERTICAL);
			linearLayout.setGravity(17);
			linearLayout.setLayoutParams(layoutParams);

			//Textview
			final TextView textView = new TextView(context);
			String str = SliderString(featurenum, 0);
			//textView.setTypeface(google(context));

			if (str != null) //Show text progress instead number
				textView.setText(feature + " : " + str);
			else  //If string is null, show number instead
				textView.setText(feature + " : " + prog);
			textView.setTextSize(13.0f);
			textView.setGravity(3);
        textView.setTextColor(Color.WHITE);
			textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
			textView.setPadding(5, 0, 0, 0);

			//Seekbar
			SeekBar seekBar = new SeekBar(context);
			seekBar.setMax(max);
		seekBar.getProgressDrawable().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.MULTIPLY);
        seekBar.getThumb().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.MULTIPLY);
			seekBar.setPadding(25, 10, 35, 10);
			seekBar.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
			LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
			layoutParams2.bottomMargin = 10;
			seekBar.setLayoutParams(layoutParams2);
			seekBar.setProgress(prog);

			final TextView textView2 = textView;
			seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					public void onStartTrackingTouch(SeekBar seekBar) {
					}

					public void onStopTrackingTouch(SeekBar seekBar) {
					}

					int l;

					public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
						if (l < i) {            
						}
						l = i;
						String str = SliderString(featurenum, i);

						interInt.OnWrite(i);
						TextView textView = textView2;

						if (str != null)
							textView.setText(feature + " : " + str);
						else
							textView.setText(feature + " : " + i);
					}
				});

			linearLayout.addView(textView);
			linearLayout.addView(seekBar);
			getChildOfScroll().addView(linearLayout);
		
    }
    
	public void Memory(boolean g, String search, String edit, String onstr, int[] ranges, int flag) {

        final MemoryScanner mem=new MemoryScanner();
        // mem.setRanges(new int[]{Ranges.ANONYMOUS});
        mem.setRanges(ranges);
        mem.clearResults();
        if (g==true)
        {
            mem.searchNumber(search, flag);
            mem.editAll(edit, flag,0x0);
            Toast.makeText(context, onstr, 1).show();
        }
        else
        {
            mem.searchNumber(edit, flag);
            mem.editAll(search, flag,0x0);
            Toast.makeText(context, onstr, 1).show();
        }
        mem.clearResults();

    }
	public void addButton(final int featNum, String feature, final ibt interfaceBtn) {
		final GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        String str2 = "#FFFF0000";
        gradientDrawable.setColor(Color.parseColor(str2));
        gradientDrawable.setStroke(3, Color.parseColor(str2));
        gradientDrawable.setCornerRadius(18.0f);
        final GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable2.setColor(0);
        gradientDrawable2.setStroke(3, Color.parseColor(str2));
        gradientDrawable2.setCornerRadius(18.0f);

        final Button button = new Button(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(7, 5, 7, 5);
        button.setText(feature);
        button.setTextColor(Color.parseColor("#F5F5F5"));
		//button.setTypeface(google(context));
        button.setTextSize(14.4f);
        button.setAllCaps(false);
        button.setBackgroundColor(Color.TRANSPARENT);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, dpi(40));
        button.setPadding(3, 3, 3, 3);
        layoutParams2.bottomMargin = 0;
        button.setLayoutParams(layoutParams2);
        final String gays2 = feature;
        button.setOnClickListener(new View.OnClickListener() {
                private boolean isActive = true;
                boolean gaa;
                public void onClick(View v) {
                    
                    switch (featNum) {
                      /*  case 3:
                            
                            gaa = !gaa;
                            if (gaa) {
                                Memory(true, "89","68","Total: ",new int[]{Ranges.ANONYMOUS}, Flags.DWORD);
                            } else {
                                Memory(false, "89","68","TestOff",new int[]{Ranges.ANONYMOUS}, Flags.DWORD);
                            }
                            
                            break;*/
                    }
                    interfaceBtn.OnWrite();
                    if (isActive) {
                        button.setText(Html.fromHtml("<b>" + gays2 + "</b>"));
                        button.setTextColor(Color.parseColor("#76FF03"));
                        button.setTextSize(15.7f);
                        Toast.makeText(context, Html.fromHtml("ON"), Toast.LENGTH_SHORT).show();
                        button.setBackgroundColor(Color.parseColor("#30FFFFFF"));
                        isActive = false;
                        return;
                    }
                    button.setText(gays2);
                    button.setTextColor(Color.parseColor("#F5F5F5"));
                    button.setTextSize(14.4f);
                    Toast.makeText(context, Html.fromHtml("OFF"), Toast.LENGTH_SHORT).show();
                    button.setBackgroundColor(Color.TRANSPARENT);
                    isActive = true;
                   
                }
            });
		getChildOfScroll().addView(button);
		
    }

	public void addButton2(final int featNum, String feature, final ibt interfaceBtn) {
		final GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        String str2 = "#ffffffff";
        gradientDrawable.setColor(Color.parseColor(str2));
        gradientDrawable.setStroke(3, Color.parseColor(str2));
        gradientDrawable.setCornerRadius(8.0f);
        final GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable2.setColor(0);
        gradientDrawable2.setStroke(3, Color.parseColor(str2));
        gradientDrawable2.setCornerRadius(8.0f);

        final Button button2 = new Button(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(7, 5, 7, 5);
        button2.setText(feature);
        button2.setTextColor(Color.parseColor("#F5F5F5"));
        button2.setTextSize(14.4f);
		//button2.setTypeface(google(context));
        button2.setAllCaps(false);
        button2.setBackgroundColor(Color.TRANSPARENT);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, dpi(40));
        button2.setPadding(3, 3, 3, 3);
        layoutParams2.bottomMargin = 0;
        button2.setLayoutParams(layoutParams2);
        final String gays2 = feature;
        button2.setOnClickListener(new View.OnClickListener() {
               // private boolean game;
                public void onClick(View v) {
                    switch (featNum) {
                        
                            }
					button2.setBackgroundColor(Color.parseColor("#30FFFFFF"));
					button2.setTextSize(15.7f);
					button2.setText(Html.fromHtml("<b>" + gays2 + "</b>"));
					final  Handler handler = new Handler();
					handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								button2.setBackgroundColor(Color.TRANSPARENT);
								button2.setText(gays2);
								button2.setTextSize(14.4f);
							}
						},75);				
                    interfaceBtn.OnWrite();
                }  
            });
		getChildOfScroll().addView(button2);
		
    }	

	public LinearLayout getInfosLayout()
	{
		return infos;
	}


	public LinearLayout getChildOfScroll()
	{
		return childOfScroll;
	}

	public LinearLayout getHeaderLayout()
	{
		return headerLayout;
	}





	public LinearLayout getMenuLayout()
	{
		return menulayout;
	}



	public ScrollView getScrollView()
	{
		return scrollItems;
	}

	protected void init(Context context)
	{
		
		this.context = context;
		isIconVisible = false;
		isMenuVisible = false;
		iconView = new ImageView(context);
		closeView = new ImageView(context);
		cred = new TextView(context);
		//cred.setTypeface(google(context));

		parentBox = new FrameLayout(context);

		parentBox.setOnTouchListener(handleMotionTouch);
		wmManager = ((Activity)context).getWindowManager();
		int aditionalFlags=0;
		if (Build.VERSION.SDK_INT >= 11)
			aditionalFlags = WindowManager.LayoutParams.FLAG_SPLIT_TOUCH;
		if (Build.VERSION.SDK_INT >=  3)
			aditionalFlags = aditionalFlags | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
		wmParams = new WindowManager.LayoutParams(
			WindowManager.LayoutParams.WRAP_CONTENT,
			WindowManager.LayoutParams.WRAP_CONTENT,
			100,//initialX)
			100,//initialy
			WindowManager.LayoutParams.TYPE_APPLICATION,
			WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
			WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN |
			WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
			aditionalFlags,
			PixelFormat.TRANSPARENT
		);
		wmParams.gravity = Gravity.TOP | Gravity.LEFT;
	}

	public void setIconImage(String Icon)
	{
		
        byte[] decode = Base64.decode(Icon, 0);
        iconView.setImageBitmap(BitmapFactory.decodeByteArray(decode, 0, decode.length));
			iconView.setPadding(dpi(2), dpi(2), 0, 0);
        iconView.setImageAlpha(500);
		
		
	}
	public void setWidth(int px)
	{
		FrameLayout.LayoutParams lp=(FrameLayout.LayoutParams)menulayout.getLayoutParams();
		lp.width = px;
		menulayout.setLayoutParams(lp);
		WIDTH=px;
	}
	
	public void setHeight(int px)
	{
		FrameLayout.LayoutParams lp=(FrameLayout.LayoutParams)menulayout.getLayoutParams();
		lp.height = px;
		menulayout.setLayoutParams(lp);
		HEIGHT=px;
	}
	public int getWidth(int px)
	{
		return WIDTH;
	}

	public int getHeight(int px)
	{
		return HEIGHT;
	}
	public void setTitle(String text)
	{
		ValueAnimator colorAnim = ObjectAnimator.ofInt(cred,"textColor", Color.rgb(255,0,0), Color.rgb(255,0,0), Color.rgb(255,0,0), Color.rgb(255,0,0), Color.rgb(255,0,0), Color.parseColor("#FF0000"));
		colorAnim.setDuration(750);
		colorAnim.setEvaluator(new ArgbEvaluator());
		colorAnim.setRepeatCount(ValueAnimator.INFINITE);
		colorAnim.setRepeatMode(ValueAnimator.RESTART);
		colorAnim.start();
		cred.setText(text);
		cred.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					showIcon();
				}


			}); 
	}
	public TextView getTitleTextView()
	{
		return cred;
	}
	
	
	
	public void showIcon()
	{
        
		if (Main.hide) {
			iconView.setVisibility(View.INVISIBLE);
		} else if (!Main.hide) {
			iconView.setVisibility(View.VISIBLE);
		}
		if (!isIconVisible)
		{
			isMenuVisible = false;
			parentBox.removeAllViews();
			parentBox.addView(iconView, dpi(70),dpi(70));
			parentBox.addView(closeView,dpi(30),dpi(30));
			isIconVisible = true;
		}
	}
	public void showMenu()
	{
		if (!isMenuVisible)
		{
			isIconVisible = false;
			parentBox.removeAllViews();
			parentBox.addView(menulayout, WIDTH, HEIGHT);
			isMenuVisible = true;
		}
	}
	public int dpi(float dp)
	{
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}
	
	
	//часть дизайна меню
	public Menu(Context context)
	{
		init(context);

		WIDTH = dpi(100);
		HEIGHT = dpi(40);

		final GradientDrawable shit = new GradientDrawable();
		shit.setColor(Color.parseColor("#263238"));
		shit.setStroke(5, Color.parseColor("#18FFFF"));
		shit.setCornerRadius(35.0f);

		menulayout = new LinearLayout(context);
		menulayout.setOrientation(LinearLayout.VERTICAL);
		{//header
			headerLayout = new LinearLayout(context);

			menulayout.addView(headerLayout, -1, -2);
			///////////MENU BG COLOR
			menulayout.setBackground(shit);
			{
				ImageView minimize=new ImageView(context);
				InputStream istr=null;
				Bitmap bitmap=null;
				AssetManager assetManager = context.getAssets();
				try
				{
					istr = assetManager.open("null");
					bitmap = BitmapFactory.decodeStream(istr);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				finally
				{
					minimize.setImageBitmap(bitmap);

				}
				cred.setTextColor(Color.WHITE);
				cred.setTextSize(19);
				cred.getPaint().setElegantTextHeight(true);
				cred.setGravity(Gravity.CENTER_HORIZONTAL);

				{
					infos = new LinearLayout(context);
					infos.setOrientation(LinearLayout.VERTICAL);
					infos.addView(cred, -1, -1);
					headerLayout.addView(infos, -1, -2);
					LinearLayout.LayoutParams mnp=(LinearLayout.LayoutParams)infos.getLayoutParams();
					mnp.weight = 5;
					mnp.gravity = Gravity.CENTER;
					infos.setLayoutParams(mnp);
				}
				headerLayout.addView(minimize, dpi(30),dpi(30));
				{
					LinearLayout.LayoutParams mnp=(LinearLayout.LayoutParams)minimize.getLayoutParams();
					mnp.weight = 0;
					mnp.gravity = Gravity.RIGHT;
					minimize.setLayoutParams(mnp);
					minimize.setPadding(0, dpi(4), dpi(4), dpi(4));
					minimize.setOnClickListener(new View.OnClickListener(){

							@Override
							public void onClick(View p1)
							{
								//showIcon();
							}


						});

				}

				
				{

					closeView.setOnClickListener(new View.OnClickListener(){

							@Override
							public void onClick(View p1)
							{
							}


						}); 

				}

			}
		}
		scrollItems = new ScrollView(context);

		childOfScroll = new LinearLayout(context);


		scrollItems.addView(childOfScroll, -1, -1);
		childOfScroll.setOrientation(LinearLayout.VERTICAL);
		childOfScroll.setBackgroundColor(Color.TRANSPARENT);

		menulayout.addView(scrollItems, -1, -1);
		wmManager.addView(parentBox, wmParams);
		showMenu();
        
        showIcon();
	}



	View.OnTouchListener handleMotionTouch = new View.OnTouchListener()
	{
		private float initX;          
		private float initY;
		private float touchX;
		private float touchY;

		double clock=0;
		@Override
		public boolean onTouch(View vw, MotionEvent ev)
		{

			switch (ev.getAction())
			{
				case MotionEvent.ACTION_DOWN:

					initX = wmParams.x;
					initY = wmParams.y;
					touchX = ev.getRawX();
					touchY = ev.getRawY();
					clock = System.currentTimeMillis();
					break;

				case MotionEvent.ACTION_MOVE:
					wmParams.x = (int)initX + (int)(ev.getRawX() - touchX);

					wmParams.y = (int)initY + (int)(ev.getRawY() - touchY);


					wmManager.updateViewLayout(vw, wmParams);
					break;

				case MotionEvent.ACTION_UP:
					if (isIconVisible && (System.currentTimeMillis() < (clock + 200)))
					{
						showMenu();
					}
					break;
			}
			return true;
		}
	};
	public static interface ibt {
        void OnWrite();
    }
	
	public static interface iit {
        void OnWrite(int i);
    }
	
	private int convertDipToPixels(int i) {
        return (int) ((((float) i) * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
