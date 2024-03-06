package ne.game.objects;
import org.newdawn.slick.*;

import java.util.ArrayList;

public class EasyGame extends BasicGame {

    private MeinUfo mUfo;
    private ArrayList<MeinUfo> mUfoList;
    private Image background;
    private Crusher crusher;
    private Sound sound;
    private Sound sound1;
    private Music music;
    private int hit=0;
    private int miss=0;
    private AngelCodeFont font;

    public EasyGame() {
        super("EasyGame");
    }
    public static void main(String[] args) throws SlickException {
        AppGameContainer container = new AppGameContainer(new EasyGame());
        container.setDisplayMode(1024, 768, false);
        //container.setClearEachFrame(false);
        container.setMinimumLogicUpdateInterval(25);
        container.setTargetFrameRate(60);
        container.setShowFPS(true);
        container.start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        font = new AngelCodeFont("testdata/demo2.fnt","testdata/demo2_00.tga");
        background = new Image("assets/pics/background.png");
        mUfoList = new ArrayList<MeinUfo>();
        for(int i=1; i<=5;i++){
            mUfoList.add(new MeinUfo(100,199, new Image("assets/pics/meinufo.png")));
        }
        crusher = new Crusher(512,680, new Image("assets/pics/crusher.png"),container.getInput());

        music = new Music("testdata/kirby.ogg",true);
        sound = new Sound("testdata/burp.aif");
        sound1 = new Sound("assets/sounds/mamamia.wav");
        music.loop();
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            container.exit();

        }
        for(MeinUfo u : mUfoList) {
            if (crusher.intersects(u.getShape())){
                System.out.println("coolide");
                u.setRandomPosition();
                sound.play();
                hit ++;
            }
            if (u.getY() > 768){
                miss ++;
                u.setRandomPosition();
            }
            if (miss >= 3){
                sound1.play();
                container.exit();
            }
            u.update(delta);
        }
        crusher.update(delta);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        background.draw();
        for(MeinUfo u : mUfoList)
            u.draw(g);

        crusher.draw(g);
        font.drawString(80, 5, hit + "Hit", Color.black);
        font.drawString(80, 40, miss + "Miss" + "Hit", Color.red);
    }
}

