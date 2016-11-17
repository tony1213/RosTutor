package com.robot.et.ros;

import android.os.Bundle;
import android.view.View;

import org.ros.android.RosActivity;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

import java.net.URI;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends RosActivity {

    private MoveControler moveControler;
    private NodeConfiguration nodeConfiguration;

    public MainActivity(){
        super("ARobot","ARobot",URI.create("http://192.168.2.166:11311"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {
        moveControler = new MoveControler();
        moveControler.isPublishVelocity(false);
        nodeConfiguration = NodeConfiguration.newPublic(getRosHostname());
        nodeConfiguration.setMasterUri(getMasterUri());
        nodeMainExecutor.execute(moveControler,nodeConfiguration);
    }

    @OnClick({R.id.forward,R.id.backward,R.id.left,R.id.right,R.id.stop})
    public void execDirection(View view){
        moveControler.isPublishVelocity(true);
        switch (view.getId()) {
            case R.id.forward:
                moveControler.execMoveForword();
                break;
            case R.id.backward:
                moveControler.execMoveBackForward();
                break;
            case R.id.left:
                moveControler.execTurnLeft();
                break;
            case R.id.right:
                moveControler.execTurnRight();
                break;
            case R.id.stop:
                moveControler.isPublishVelocity(false);
                break;
        }
    }




}
