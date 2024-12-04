package com.lab6.data.entity.response

import com.google.gson.annotations.SerializedName


data class EclipseInfo(
    @SerializedName("timestamp") val timestamp: Long,
    @SerializedName("datestamp") val datestamp: String,
    @SerializedName("type") val type: String,
    @SerializedName("visibility_regions") val visibilityRegions: String
)

data class MoonInfo(
    @SerializedName("phase") val phase: Double,
    @SerializedName("phase_name") val phaseName: String,
    @SerializedName("stage") val stage: String,
    @SerializedName("illumination") val illumination: String,
    @SerializedName("age_days") val ageDays: Int,
    @SerializedName("lunar_cycle") val lunarCycle: String,
    @SerializedName("emoji") val emoji: String,
    @SerializedName("moonrise") val moonrise: String,
    @SerializedName("moonrise_timestamp") val moonriseTimestamp: Long,
    @SerializedName("moonset") val moonset: String,
    @SerializedName("moonset_timestamp") val moonsetTimestamp: Long,
    @SerializedName("moon_altitude") val moonAltitude: Double,
    @SerializedName("moon_distance") val moonDistance: Double,
    @SerializedName("moon_azimuth") val moonAzimuth: Double,
    @SerializedName("moon_parallactic_angle") val moonParallacticAngle: Double
)

data class LocationInfo(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)

data class MoonPhases(
    @SerializedName("January") val january: List<String>,
    @SerializedName("February") val february: List<String>,
    @SerializedName("March") val march: List<String>,
    @SerializedName("April") val april: List<String>,
    @SerializedName("May") val may: List<String>,
    @SerializedName("June") val june: List<String>,
    @SerializedName("July") val july: List<String>,
    @SerializedName("August") val august: List<String>,
    @SerializedName("September") val september: List<String>,
    @SerializedName("October") val october: List<String>,
    @SerializedName("November") val november: List<String>,
    @SerializedName("December") val december: List<String>
)

data class MoonPhaseResponse(
    @SerializedName("timestamp") val timestamp: Long,
    @SerializedName("datestamp") val datestamp: String,
    @SerializedName("moon") val moon: MoonInfo,
    @SerializedName("next_solar_eclipse") val nextSolarEclipse: EclipseInfo,
    @SerializedName("next_lunar_eclipse") val nextLunarEclipse: EclipseInfo,
    @SerializedName("location") val location: LocationInfo,
    @SerializedName("moon_phases") val moonPhases: MoonPhases
)

