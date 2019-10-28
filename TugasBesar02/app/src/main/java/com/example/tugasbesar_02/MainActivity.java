package com.example.tugasbesar_02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,FragmentListener {
    protected Bitmap mBitmap;
    protected Canvas mCanvas;
    protected Paint strokePaint;
    protected Button btnStart;
    protected FloatingActionButton fabPause;
    protected FloatingActionButton fabMenu;
    protected TextView tvPlayerName;
    protected TextView tvScore;
    private TextView tvWelcome;
    protected ImageView ivPlane;
    protected ImageView ivPlane2;
    protected ImageView ivBomb;
    protected ImageView ivFuel;
    protected ImageView ivReward;
    protected FrameLayout flCanvas;

    protected EnemyPlane enemyPlane;


    // buat ngatur supaya object ga kluar dari canvas
    private int planeSize;
    private int canvasHeight;
    private int lebarLayar, tinggiLayar;

    // object position
    private int planeY;
    private int plane2X, plane2Y;
    private int bombX, bombY;
    private int fuelX, fuelY;
    private int rewardX, rewardY;

    // class initialization
    protected Handler handler = new Handler();
    protected Timer timer = new Timer();

    // buat cek status 
    private boolean action_flg = false;
    private boolean activityStart = false;

    // scoring
    protected int score = 0;

    /*
    private ViewGroup mainLayout;
    private int xDelta;
    private int yDelta;

     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this.btnStart = findViewById(R.id.btn_start);
        this.fabPause = findViewById(R.id.fab_pause);
        this.fabMenu = findViewById(R.id.fab_menu);
        this.tvScore = findViewById(R.id.tv_score);
        this.tvPlayerName = findViewById(R.id.tv_playerName);
        this.tvWelcome = findViewById(R.id.tv_welcome);
        this.ivPlane = findViewById(R.id.iv_plane);
        this.ivPlane2 = findViewById(R.id.iv_plane_enemy);
        this.ivBomb = findViewById(R.id.iv_bomb);
        this.ivFuel = findViewById(R.id.iv_fuel);
        this.ivReward = findViewById(R.id.iv_reward);

        // setOnClickListener
        //this.btnStart.setOnClickListener(this);
        this.fabPause.setOnClickListener(this);
        this.fabMenu.setOnClickListener(this);

        // buat dapetin ukuran layar trus set ke ukuranLayar
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);

        lebarLayar = displaySize.x;
        tinggiLayar = displaySize.y;

        // bersihin si layar jd cuma nampilin pesawat kita doang
        //this.ivPlane2.setX(-80);
        //this.ivPlane2.setY(-80);
        this.ivBomb.setX(-80);
        this.ivBomb.setY(-80);
        this.ivFuel.setX(-80);
        this.ivFuel.setY(-80);
        this.ivReward.setX(-80);
        this.ivReward.setY(-80);


        tvScore.setText("Score : " + this.score);

        this.enemyPlane = new EnemyPlane(this);


    }

    public void changePosition(){
        hitStatus();

        // fuel
        fuelX -= 3;
        if(fuelX < 0){
            fuelX = lebarLayar + 30;
            fuelY = (int) Math.floor(Math.random() * (tinggiLayar - ivFuel.getHeight()));
        }
        ivFuel.setX(fuelX);
        ivFuel.setY(fuelY);

        // reward
        rewardX -= 20;
        if(rewardX < 0){
            rewardX = lebarLayar + 15;
            rewardY = (int) Math.floor(Math.random() * (tinggiLayar - ivReward.getHeight()));
        }
        ivReward.setX(rewardX);
        ivReward.setY(rewardY);

        /*
        // plane2
        plane2X -= 10;
        if(plane2X < 0){
            plane2X = lebarLayar + 20;
            plane2Y = (int) Math.floor(Math.random() * (tinggiLayar - ivPlane2.getHeight()));
        }
        ivPlane2.setX(plane2X);
        ivPlane2.setY(plane2Y);

         */


        this.enemyPlane.spawn();

        // bomb
        bombX -= 5;
        if(bombX < 0){
            bombX = lebarLayar + 12;
            bombY = (int) Math.floor(Math.random() * (tinggiLayar - ivBomb.getHeight()));
        }
        ivBomb.setX(bombX);
        ivBomb.setY(bombY);


        // move plane, dibuat kalo touch nanti naek, kalo release nanti turun
        if(action_flg == true){
            // touching
            planeY -= 20;
        }
        else{
            // release
            planeY += 20;
        }

        // check plane position
        if(planeY < 0){
            planeY = 0;
        }
        if(planeY > (canvasHeight - planeSize)){
            planeY = canvasHeight - planeSize;
        }

        ivPlane.setY(planeY);

    }

    /*
    kalo kena pesawat score -10 >> endgame
    kalo kena bom score -20 >> endgame
    kalo kena reward +=50
    kalo kena fuel +=20
     */
    public void hitStatus(){

        int plane2DiaX = plane2X + (ivPlane2.getWidth() / 2);
        int plane2DiaY = plane2Y + (ivPlane2.getHeight() / 2);

        int bombDiaX = bombX + (ivBomb.getWidth() / 2);
        int bombDiaY = bombY + (ivBomb.getHeight() / 2);

        // ngitungnya kalo kena center si ivplane(plane1) berarti kena, jadi objek yg dikenain ilang
        boolean nempel = 0<=plane2DiaX;
        boolean sejajarDalamP1 = ((plane2DiaY <= planeSize) || (bombDiaY<=planeSize));
        boolean samaTinggiDalamP1 = ((planeY<=plane2DiaY)||(planeY<=bombY));
        boolean didalamP1 = plane2DiaY<=planeY+planeSize;

        if(nempel && sejajarDalamP1 && samaTinggiDalamP1 && didalamP1){
            this.score -= 10;
            plane2X -= 10;
        }


    }


    public boolean onTouchEvent(MotionEvent motionEvent){

        if(activityStart == false){
            activityStart = true;

            this.tvWelcome.setVisibility(View.GONE);

            /*
            frame height and plane height nya baru diset disini soalnya di method
            onCreate() si UI nya belom set ama screennya. baris ini buat ngejaga
            supaya si planenya ga kluar dari canvas
            */
            this.flCanvas = findViewById(R.id.frame_canvas);
            canvasHeight = flCanvas.getHeight();

            this.planeY = (int) ivPlane.getY();

            planeSize = ivPlane.getHeight();


            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePosition();
                        }
                    });
                }
            }, 0, 20);  //manggil method changePosition setiap 20ms
        }
        else{
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                action_flg = true;
            }
            else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                action_flg = false;
            }
        }


        return true;
    }


    /*
    public void initiateCanvas() {
        // 1. Create Bitmap
        this.mBitmap = Bitmap.createBitmap(this.ivCanvas.getWidth(), this.ivCanvas.getHeight(), Bitmap.Config.ARGB_8888);

        // 2. Associate the bitmap to the ImageView.
        this.ivCanvas.setImageBitmap(mBitmap);

        // 3. Create a Canvas with the bitmap.
        this.mCanvas = new Canvas(mBitmap);
        this.resetCanvas();

        // new paint for stroke + style (Paint.Style.STROKE)
        this.strokePaint = new Paint();
        this.strokePaint.setColor(Color.BLUE);

        //create pesawat
        /*
        Pesawat p = new Pesawat(100, 100, 100);
        p.drawTriangle(this.mCanvas, this.strokePaint);
        p.setOnTouchListener(onTouchListener());
        p.getOnTouchListener();



        //resetCanvas
        this.ivCanvas.invalidate();



    public void resetCanvas() {
        // 4. Draw canvas background
        // Fill the entire canvas with solid color.
        int mColorBackground = ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null);
        mCanvas.drawColor(mColorBackground);

        // 5. force draw
        this.ivCanvas.invalidate();
    }

     */

    @Override
    public void onClick(View view) {
       if(btnStart.getId() == view.getId()) {
           //this.initiateCanvas();
       }
       else if(view.getId() == this.fabPause.getId()){
           //jika ic_pause >> pause game
           FragmentTransaction ft = getFragmentManager().beginTransaction();
           PauseFragment pauseDialogFragment = new PauseFragment();
           //pauseDialogFragment.show(ft,this.tv_score.getText().toString()); <<Ini bingung salah dimana
       }
       else if(view.getId() == this.fabMenu.getId()){
           //jika ic_menu >> tampilkan menu
           FragmentTransaction ft = getFragmentManager().beginTransaction();
           MenuFragment menuDialogFragment = new MenuFragment();
           //menuDialogFragment.show(ft,this.tv_playerName.getText().toString()); <<Ini bingung salah dimana
       }
    }

    
    /*
    private View.OnTouchListener onTouchListener(){
       return new View.OnTouchListener() {
           @SuppressLint("ClickableViewAccessibility")
           @Override
           public boolean onTouch(View view, MotionEvent motionEvent) {
               final int x = (int) motionEvent.getRawX();
               final int y = (int) motionEvent.getRawY();

               switch(motionEvent.getAction() & MotionEvent.ACTION_MASK){
                   case MotionEvent.ACTION_DOWN:
                       LinearLayout.LayoutParams lParams = (LinearLayout.LayoutParams)
                               view.getLayoutParams();

                       //xDelta = x - lParams.leftMargin;
                       //yDelta = y - lParams.topMargin;
                       break;

                   case MotionEvent.ACTION_UP:
                       Toast.makeText(MainActivity.this,
                               "thanks for new location!", Toast.LENGTH_SHORT)
                               .show();
                       break;

                   case MotionEvent.ACTION_MOVE:
                       LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view
                               .getLayoutParams();
                       layoutParams.leftMargin = x - xDelta;
                       layoutParams.topMargin = y - yDelta;
                       layoutParams.rightMargin = 0;
                       layoutParams.bottomMargin = 0;
                       view.setLayoutParams(layoutParams);
                       break;
               }
               //mainLayout.invalidate();
               return true;
           }
       };
    }

    /*
    public void drawMusuh(Musuh musuh)
    {
        this.resetCanvas();
        this.strokePaint = new Paint();
        this.strokePaint.setColor(Color.BLUE);

        this.mCanvas.drawCircle(musuh.getX(),musuh.getY(),musuh.getRadian() ,this.strokePaint);

        this.ivCanvas.invalidate();

    }
     */

    @Override
    public void closeApp() {
        this.moveTaskToBack(true);
        this.finish();
    }




}
