package com.robot.et.ros;

import android.os.Bundle;

import com.robot.et.android_ros_common.view.VirtualJoystickView;

import org.ros.address.InetAddressFactory;
import org.ros.android.RosActivity;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

import java.net.URI;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends RosActivity {

    @BindView(R.id.virtualjoystick)
    VirtualJoystickView virtualJoystickView;
    private static final String Master_URI = "http://192.168.2.166:11311";

    public MainActivity() {
        super("ARobot", "ARobot", URI.create(Master_URI));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {
        NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(getRosHostname());
        nodeConfiguration.setMasterUri(getMasterUri());
        nodeMainExecutor.execute(virtualJoystickView, nodeConfiguration.setNodeName("virtual_joystick"));
    }
}
