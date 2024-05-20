package com.itu.mbds

import grails.converters.JSON
import grails.converters.XML
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
class ApiController {

    UserService userService

    /**
     * Ressource User Singleton
     * Gère : GET / PUT / PATCH / DELETE
     */
    def user() {
        if (!params.id)
            return response.status = 400
        def userInstance = User.get(params.id)
        if (!userInstance)
            return response.status = 404
        switch (request.getMethod()) {
            case "GET":
                renderThis(userInstance, request.getHeader("Accept"))
                break;
            case "PUT":
                userInstance.properties = request.getJSON()
                break;
            case "PATCH":
                break;
            case "DELETE":
                break;
            default:
                return response.status = 405
                break;
        }
        return response.status = 406
    }
    /**
     * Ressource Collection User
     * Gère : GET / POST
     */
    def users() {

    }

    def renderThis(Object instance, String accept) {
        switch (accept) {
            case "xml":
            case "text/xml":
            case "application/xml":
                render instance as XML
                break;
            case "json":
            case "text/json":
            case "application/json":
            default:
                render instance as JSON
                break;
        }
    }
}

