## 适配大屏原理和方法
###原理 
修改设备屏幕密度，实现大屏适配。
### 方法
1. 重写Activity方法 `getResources()`
		
				@Override
			    public Resources getResources() {
			        if (isUseSystemConfig()) { // 使用系统配置
			            return super.getResources();
			        } else {
			            Resources res = super.getResources();
			            Configuration config;
			            config = res.getConfiguration();
			            config.setToDefaults(); // 重置为默认
			
			            config.densityDpi = DensityHelper.matchTheoryDpi(this);
			            /**
			             *  0.8   0.9  1.0  1.1  1.2  1.35  1.5  <br/>
			             *   |    |     |    |    |    ｜　　｜  <br/>
			             * 极小 特小   小　 中   大   特大  超大 <br/>
			             */
			            res.updateConfiguration(config, res.getDisplayMetrics());
			            return res;
			        }
			    }
	    
2. 设置需要修改设备屏幕密度的标志(例如：渠道号)

```
    /**
     * 是否使用系统的Config
     *
     * @return true 跟随系统
     */
    protected boolean isUseSystemConfig() {
        //如果是大屏渠道直接适配大屏
        if (TextUtils.equals(ChannelUtil.getChannel(), BIG_SCREEN_CHANNEL)) {
            return false;
        }

        // 浙江日报部分大屏设备信息
        if (TextUtils.equals(Build.MODEL, "DS830")
                && TextUtils.equals(Build.BOARD, "exdroid")
                && TextUtils.equals(Build.BRAND, "Allwinner")
                && TextUtils.equals(Build.MANUFACTURER, "Allwinner")
                && TextUtils.equals(Build.ID, "KTU84Q")
                && TextUtils.equals(Build.DEVICE, "octopus-perf")) {
            return false;
        }
        return true;
    }
```
    
具体可参考工程 match-big-screen