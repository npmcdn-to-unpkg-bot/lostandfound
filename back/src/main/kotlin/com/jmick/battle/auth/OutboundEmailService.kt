package com.jmick.battle.auth

import com.sendgrid.SendGrid
import com.sendgrid.SendGridException
import org.slf4j.LoggerFactory


class OutboundEmailService(val sendGridUser: String, val sendGridPass: String) {

    val logger = LoggerFactory.getLogger(OutboundEmailService::class.java)

    fun send(email: SendGrid.Email) : Boolean {
        val sendgrid = SendGrid(sendGridUser, sendGridPass)
        try {
            val response = sendgrid.send(email)
            if (response.code != 200) {
                return false
            }
        } catch (e : SendGridException) {
            logger.error("Sendgrid API call failed for reason", e)
            return false
        }
        return true
    }
}