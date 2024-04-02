package kw.black.listener;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import kw.black.group.BPKuaiGroup;

public class MyClickListener extends ClickListener {
    private BPKuaiGroup kuaiGroup;
    public MyClickListener(BPKuaiGroup kuaiGroup){
        this.kuaiGroup = kuaiGroup;
        kuaiGroup.addListener(this);
    }

    public BPKuaiGroup getKuaiGroup() {
        return kuaiGroup;
    }
}

