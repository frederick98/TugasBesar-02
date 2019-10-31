package com.example.tugasbesar_02;

import androidx.appcompat.app.AppCompatActivity;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,FragmentListener {
    protected Bitmap mBitmap;
    protected Canvas mCanvas;
    protected Paint strokePaint;
    protected FloatingActionButton fabPause;
    protected FloatingActionButton fabMenu;
    protected TextView tvPlayerName;
    public TextView tvScore;
    protected TextView tvWelcome;
    protected ImageView ivPlane;
    protected FrameLayout flCanvas;


    // class initialization
    protected Handler handler = new Handler();
    protected Timer timer = new Timer();
    protected Plane plane;
    protected EnemyPlane enemyPlane;
    protected Bomb bomb;
    protected Fuel fuel;
    protected Reward reward;
    protected MyAsyncTask requester;
    protected String url;


    // buat ngatur supaya object ga kluar dari canvas
    private int planeHeight;
    private int canvasHeight;

    // object position
    private int planeY;

    // buat cek status 
    protected boolean action_flg = false;
    protected boolean activityStart = false;

    // scoring
    protected int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fabPause = findViewById(R.id.fab_pause);
        this.fabMenu = findViewById(R.id.fab_menu);
        this.tvScore = findViewById(R.id.tv_score);
        this.tvPlayerName = findViewById(R.id.tv_playerName);
        this.tvWelcome = findViewById(R.id.tv_welcome);
        this.ivPlane = findViewById(R.id.iv_plane);

        // setOnClickListener
        this.fabPause.setOnClickListener(this);
        this.fabMenu.setOnClickListener(this);

        tvScore.setText("Score : " + this.score);

        this.plane = new Plane(this);
        this.enemyPlane = new EnemyPlane(this);
        this.bomb = new Bomb(this);
        this.fuel = new Fuel(this);
        this.reward = new Reward(this);

        this.url = "http://p3b.labftis.net/api.php";
    }

    public void changePosition() {
        // method ini melakukan pengecekan jika objek kena ke pesawat
        hitStatus();

        // spawn object
        this.enemyPlane.spawn();
        this.bomb.spawn();
        this.fuel.spawn();
        this.reward.spawn();

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
        if(planeY > (canvasHeight - planeHeight)){
            planeY = (canvasHeight - planeHeight);
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
        int plane2DiaX = this.enemyPlane.getX() + (this.enemyPlane.getWidth() / 2);
        int plane2DiaY = this.enemyPlane.getY() + (this.enemyPlane.getHeight() / 2);

        // ngitungnya kalo kena center si ivplane(plane1) berarti kena, jadi objek yg dikenain ilang
       boolean nempel=ivPlane.getX()==this.enemyPlane.getX();
       boolean nempel2 =ivPlane.getY()==this.enemyPlane.getY();
        //boolean nempel = 0<=plane2DiaX;
        //boolean sejajarDalamP1 = ((plane2DiaY <= planeSize)); //|| (bombDiaY<=planeSize));
        //boolean samaTinggiDalamP1 = ((planeY<=plane2DiaY)); //||(planeY<=bombY));
        //boolean didalamP1 = plane2DiaY<=planeY+planeSize;

        if(nempel && nempel2){
            this.score -= 10;
            this.tvScore.setText("Score: " + this.score);
            this.enemyPlane.setX(this.enemyPlane.x -= 10);

            // stop playing
            this.timer.cancel();
            this.timer = null;
            this.gameSelesai();
            androidx.fragment.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            GameOverFragment gameOverDialogFragment = new GameOverFragment();
            gameOverDialogFragment.show(ft,this.tvPlayerName.getText().toString());
        }

        this.tvScore.setText("Score : "+this.score);
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

            planeHeight = ivPlane.getHeight();


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
       if(view.getId() == this.fabPause.getId()){
           //jika ic_pause >> pause game
           androidx.fragment.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
           PauseFragment pauseDialogFragment = new PauseFragment();
           pauseDialogFragment.show(ft,"");
       }
       else if(view.getId() == this.fabMenu.getId()){
           //jika ic_menu >> tampilkan menu
           androidx.fragment.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
           MenuFragment menuDialogFragment = new MenuFragment();
           menuDialogFragment.show(ft,"");
       }
    }

    @Override
    public void closeApp() {
        this.moveTaskToBack(true);
        this.finish();
    }

    //ketika gameover, panggil method ini untuk post scorenya ke wbs
    public void gameSelesai(){
        requester = new MyAsyncTask(this);
        requester.execute(this.tvScore.getText().toString(),url);
    }

    public int getScore(){
        return this.score;
    }
}
