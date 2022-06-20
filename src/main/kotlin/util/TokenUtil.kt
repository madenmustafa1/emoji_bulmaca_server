package util

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import java.util.*


class TokenUtil {


    fun createToken(value: String): String? {
        val uuid = UUID.randomUUID()
        val token = try {
            val algorithm = Algorithm.HMAC256(Constants.secretKey)
            JWT.create()
                .withIssuer("auth0")
                .withClaim("email", value)
                .withClaim("uuid", uuid.toString())
                .withExpiresAt(expiryDate())
                .sign(algorithm)
        } catch (exception: JWTCreationException) {
            null
        }
        return token
    }

    private fun expiryDate(): Date {
        val calendar = Calendar.getInstance()

        val calFebruary = Calendar.getInstance()
        calFebruary[Calendar.MONTH] = Calendar.FEBRUARY

        calendar.add(Calendar.MONTH, 1)
        calendar[Calendar.DATE] = calendar.getActualMinimum(Calendar.DAY_OF_MONTH)
        return calendar.time
    }


    fun verifyToken(token: String?//, email: String
    ): Boolean{
        if (token == null) return false

        val replaceAuth = token.replace("Bearer ", "")

        return try {
            val algorithm = Algorithm.HMAC256(Constants.secretKey)
            val verifier: JWTVerifier = JWT.require(algorithm)
                .withIssuer("auth0")
                //.withClaim("email", email)
                .build() //Reusable verifier instance
            val jwt: DecodedJWT = verifier.verify(replaceAuth)
            true
        } catch (exception: JWTVerificationException) {
            //Invalid signature/claims
            false
        }
    }
}