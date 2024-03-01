package kw.black;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import kw.black.group.KuaiGroup;

public class MyClickListener extends ClickListener {
    private KuaiGroup kuaiGroup;
    public MyClickListener(KuaiGroup kuaiGroup){
        this.kuaiGroup = kuaiGroup;
        kuaiGroup.addListener(this);
    }

    public KuaiGroup getKuaiGroup() {
        return kuaiGroup;
    }
}

