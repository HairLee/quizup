package com.elcom.com.quizupapp.ui.activity.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by admin on 3/8/2018.
 */
class Person(val userName: String, val gender: String)   {


    private var id: Int = 0

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }



    @SerializedName("rom")
    @Expose
    private var rom: String? = null
    @SerializedName("screenSize")
    @Expose
    private var screenSize: String? = null
    @SerializedName("backCamera")
    @Expose
    private var backCamera: String? = null
    @SerializedName("companyName")
    @Expose
    private var companyName: String? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("frontCamera")
    @Expose
    private var frontCamera: String? = null
    @SerializedName("battery")
    @Expose
    private var battery: String? = null
    @SerializedName("operatingSystem")
    @Expose
    private var operatingSystem: String? = null
    @SerializedName("processor")
    @Expose
    private var processor: String? = null
    @SerializedName("url")
    @Expose
    private var url: String? = null
    @SerializedName("ram")
    @Expose
    private var ram: String? = null

    fun getRom(): String? {
        return rom
    }

    fun setRom(rom: String) {
        this.rom = rom
    }

    fun getScreenSize(): String? {
        return screenSize
    }

    fun setScreenSize(screenSize: String) {
        this.screenSize = screenSize
    }

    fun getBackCamera(): String? {
        return backCamera
    }

    fun setBackCamera(backCamera: String) {
        this.backCamera = backCamera
    }

    fun getCompanyName(): String? {
        return companyName
    }

    fun setCompanyName(companyName: String) {
        this.companyName = companyName
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getFrontCamera(): String? {
        return frontCamera
    }

    fun setFrontCamera(frontCamera: String) {
        this.frontCamera = frontCamera
    }

    fun getBattery(): String? {
        return battery
    }

    fun setBattery(battery: String) {
        this.battery = battery
    }

    fun getOperatingSystem(): String? {
        return operatingSystem
    }

    fun setOperatingSystem(operatingSystem: String) {
        this.operatingSystem = operatingSystem
    }

    fun getProcessor(): String? {
        return processor
    }

    fun setProcessor(processor: String) {
        this.processor = processor
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String) {
        this.url = url
    }

    fun getRam(): String? {
        return ram
    }

    fun setRam(ram: String) {
        this.ram = ram
    }

}