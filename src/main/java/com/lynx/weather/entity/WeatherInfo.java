package com.lynx.weather.entity;

import com.google.gson.annotations.Expose;

/**
 * @author chris.liu
 * @version 3/23/14 3:19 PM
 */
public class WeatherInfo {
	@Expose
	private String[] temp; // 气温
	@Expose
	private String[] weather; // 天气
	@Expose
	private String suggestion; // 建议
    @Expose
    private String[] wind; // 风力

    public String[] getTemp() {
        return temp;
    }

    public void setTemp(String[] temp) {
        this.temp = temp;
    }

    public String[] getWeather() {
        return weather;
    }

    public void setWeather(String[] weather) {
        this.weather = weather;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String[] getWind() {
        return wind;
    }

    public void setWind(String[] wind) {
        this.wind = wind;
    }
}
