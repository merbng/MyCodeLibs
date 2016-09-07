package com.app.merbng.mycodelibs.StudyBuildModel;

/**
 * Created by merbng on 2016/9/4.
 */
public abstract class Computer {

    protected int mCpuCore = 1;
    protected int mRamSize = 0;
    protected String mOs = "Dos";

    public Computer() {
    }

    // 设置CPU核心数  
    public abstract void setCPU(int core);

    // 设置内存  
    public abstract void setRAM(int gb);

    // 设置操作系统  
    public abstract void setOs(String os);

    public class AppleComputer extends Computer {
        protected AppleComputer() {
        }

        @Override
        public void setCPU(int core) {
            this.mCpuCore = core;
        }

        @Override
        public void setRAM(int gb) {
            this.mRamSize = gb;
        }

        @Override
        public void setOs(String os) {
            mOs = os;
        }
    }

}
