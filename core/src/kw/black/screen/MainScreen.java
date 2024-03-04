package kw.black.screen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.screen.BaseScreen;

public class MainScreen extends BaseScreen {
    public MainScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        Image startBtn = new Image(Asset.getAsset().getTexture("allBtn.png"));
        addActor(startBtn);
        startBtn.setPosition(360,640, Align.center);
        startBtn.setOrigin(Align.center);
        startBtn.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setScreen(new BlackPuzzleScreen(game));
//                setScreen(new TerisScreen(game));
            }
        });
    }
}
