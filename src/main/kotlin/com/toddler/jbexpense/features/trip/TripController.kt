package com.toddler.jbexpense.features.trip

import com.toddler.jbexpense.common.UserContextHolder
import com.toddler.jbexpense.features.trip.requestObj.CreateTripRP
import com.toddler.jbexpense.features.trip.requestObj.ShareTripRP
import com.toddler.jbexpense.features.trip.response.CreatedTripDTO
import com.toddler.jbexpense.features.trip.response.GetAllTripResponse
import com.toddler.jbexpense.features.trip.response.SharedTripDTO
import com.toddler.jbexpense.features.trip.response.TripMemberDTO
import com.toddler.jbexpense.features.trip.response.TripMembersDTO
import com.toddler.jbexpense.trip.Trip
import com.toddler.jbexpense.trip.toDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/trip")
class TripController(
    val tripService: TripService
) {

    @GetMapping
    fun getAllTrip(): ResponseEntity<GetAllTripResponse> {
        val tripList = tripService.getAllTrip()

        return ResponseEntity(GetAllTripResponse(tripList.map { it.toDto() }), HttpStatus.OK)
    }

    @PostMapping("/create")
    fun createTrip(
        @RequestBody createTripRP: CreateTripRP
    ): ResponseEntity<CreatedTripDTO> {
        val userId = UserContextHolder.getUserId()!!.toLong()
        val createdTrip = tripService.createTrip(createTripRP, userId)
        return ResponseEntity(CreatedTripDTO(createdTrip = createdTrip.toDto()), HttpStatus.CREATED)
    }

    @PostMapping("/{tripId}/share")
    fun shareTrip(
        @PathVariable tripId: Long,
        @RequestBody shareTripRP: ShareTripRP
    ): ResponseEntity<TripMemberDTO> {
        val userId = UserContextHolder.getUserId()!!.toLong()
        val tripMember = tripService.shareTrip(tripId, shareTripRP.userEmail, userId)
        return ResponseEntity(TripMemberDTO(tripMember = tripMember), HttpStatus.CREATED)
    }

    @GetMapping("/shared")
    fun getSharedTrips(): ResponseEntity<SharedTripDTO> {
        val userId = UserContextHolder.getUserId()!!.toLong()
        val trips = tripService.getSharedTrips(userId)
        return ResponseEntity(SharedTripDTO(trips = trips), HttpStatus.OK)
    }

    @GetMapping("/{tripId}/members")
    fun getTripMembers(@PathVariable tripId: Long): ResponseEntity<TripMembersDTO> {
        val members = tripService.getTripMembers(tripId)
        return ResponseEntity(TripMembersDTO(members = members), HttpStatus.OK)
    }
}