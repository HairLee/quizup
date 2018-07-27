package com.elcom.com.quizupapp.ui.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.support.annotation.NonNull
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.BottomSheetBehavior
import android.view.View
import android.support.design.widget.CoordinatorLayout
import com.elcom.com.quizupapp.R


@SuppressLint("ValidFragment")
/**
 * Created by Hailpt on 4/10/2018.
 */
class BottomSheetDialogCustom :  BottomSheetDialogFragment() {


    private val mBottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {

        override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                dismiss()
            }

        }

        override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) {}
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog?, style: Int) {
        super.setupDialog(dialog, style)
        super.setupDialog(dialog, style)
        val contentView = View.inflate(context, R.layout.bottom_sheet_dialog, null)
        dialog!!.setContentView(contentView)

        val params = (contentView.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior

        if (behavior != null && behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(mBottomSheetBehaviorCallback)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }
}