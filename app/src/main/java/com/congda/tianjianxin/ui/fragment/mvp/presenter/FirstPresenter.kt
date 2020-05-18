package com.congda.tianjianxin.ui.fragment.mvp.presenter

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.view.View
import androidx.fragment.app.FragmentActivity
import cc.shinichi.library.ImagePreview
import cc.shinichi.library.bean.ImageInfo
import cc.shinichi.library.view.listener.OnBigImagePageChangeListener
import com.congda.baselibrary.mvp.BasePresenter
import com.congda.baselibrary.utils.IMSavePhotoUtil
import com.congda.baselibrary.utils.glide.IMChooseUtils
import com.congda.baselibrary.widget.dialog.IMSheetDialog
import com.congda.tianjianxin.ui.fragment.mvp.contract.FirstContract
import com.congda.tianjianxin.ui.fragment.mvp.contract.ListFirstContract
import com.congda.tianjianxin.ui.fragment.mvp.model.FirstModel

class FirstPresenter : BasePresenter<FirstContract.Model, FirstContract.View>(),
    OnBigImagePageChangeListener {

    override fun createModel(): FirstContract.Model {
        return FirstModel()
    }
    fun showSheetView(context: Context) {
        IMSheetDialog.Builder(context)
            .addSheet("拍照", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
                view.showToast("拍照")
            })
            .addSheet("选择图片", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
                IMChooseUtils.choosePhotoForResult(context,10001,9)
            })
            .create().show()
    }

    fun showBigImageView(context: FragmentActivity) {
        val imageInfoList: MutableList<ImageInfo> = mutableListOf();
        var imageInfo = ImageInfo()
        imageInfo.setOriginUrl("http://65.52.160.124:9050/group1/M00/00/55/CgABFV6buVuAZCFqAAIImhjoeGY62.jpeg")
        imageInfo.setThumbnailUrl("http://65.52.160.124:9050/group1/M00/00/55/CgABFV6buVuADaMRAAAOyggtizU42.jpeg")
        imageInfoList.add(0, imageInfo)

        imageInfo = ImageInfo()
        imageInfo.setOriginUrl("http://65.52.160.124:9050/group1/M00/00/55/CgABFV6buX2ASWhNAAFvQV_a_HU53.jpeg")
        imageInfo.setThumbnailUrl("http://65.52.160.124:9050/group1/M00/00/55/CgABFV6buX2AaOV8AAAXF06XiH860.jpeg")
        imageInfoList.add(0, imageInfo)

        imageInfo = ImageInfo()
        imageInfo.setOriginUrl("http://65.52.160.124:9050/group1/M00/00/55/CgABFV6buX2AD-7EAAEOr8qHdJk72.jpeg")
        imageInfo.setThumbnailUrl("http://65.52.160.124:9050/group1/M00/00/55/CgABFV6buX2AEwwDAAAfXguPxv477.jpeg")
        imageInfoList.add(0, imageInfo)


        ImagePreview.getInstance() // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好；
            .setContext(context)
            .setLoadStrategy(ImagePreview.LoadStrategy.Default)
            .setZoomTransitionDuration(1000)
            .setImageInfoList(imageInfoList) //设置是否显示下载按钮
            //设置是否显示下载按钮
            .setShowDownButton(false)
            // 点击回调
            .setBigImageClickListener{ activity: Activity, view: View, i: Int ->{}
            }
            .setBigImageLongClickListener{ activity: Activity, view: View, i: Int ->
                handleBigImageLongClickListener(activity,i,imageInfoList);
                return@setBigImageLongClickListener true
            }
            .setBigImagePageChangeListener(this)
            .start()
    }

    /**
     * 长按图片处理
     */
    private fun handleBigImageLongClickListener(activity: Activity, i: Int,imageInfoList: MutableList<ImageInfo>) {
        IMSheetDialog.Builder(activity)
            .addSheet("保存图片") { dialog : DialogInterface, which :Int ->
                dialog.dismiss()
                ImagePreview.getInstance().setFolderName("JinXin")
//                IMSavePictureUtils.downloadPicture(activity, imageInfoList.get(i).getOriginUrl())

                IMSavePhotoUtil.saveUrlToPhoto(activity,imageInfoList.get(i).getOriginUrl(),null);
            }
            .addSheet("分享好友") { dialog : DialogInterface, which :Int ->
                dialog.dismiss()
            }.create().show();
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
    }
}


