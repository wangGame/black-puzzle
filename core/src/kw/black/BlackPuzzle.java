package kw.black;

import com.kw.gdx.BaseGame;
import com.kw.gdx.resource.annotation.GameInfo;

import kw.black.screen.MainScreen;

@GameInfo(width = 720,height = 1280)
public class BlackPuzzle extends BaseGame {

    @Override
    protected void loadingView() {
        super.loadingView();
        setScreen(new MainScreen(this));
    }
}
