package com.itu.mbds

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class PostController {

    PostService postService
    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        User userInstance = (User) springSecurityService.getCurrentUser()
        def postList
        if (userInstance.getAuthorities().contains(Role.findByAuthority('ROLE_USER')))
            postList = Post.findAllByAuthor(userInstance)
        else
            postList = postService.list(params)
        params.max = Math.min(max ?: 10, 100)
        respond postList, model: [postCount: postService.count()]
    }

    def show(Long id) {
        respond postService.get(id)
    }

    def create() {
        respond new Post(params)
    }

    def save(Post post) {
        if (post == null) {
            notFound()
            return
        }

        try {
            postService.save(post)
        } catch (ValidationException e) {
            respond post.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'post.label', default: 'Post'), post.id])
                redirect post
            }
            '*' { respond post, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond postService.get(id)
    }

    def update(Post post) {
        if (post == null) {
            notFound()
            return
        }

        try {
            postService.save(post)
        } catch (ValidationException e) {
            respond post.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'post.label', default: 'Post'), post.id])
                redirect post
            }
            '*' { respond post, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        postService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'post.label', default: 'Post'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'post.label', default: 'Post'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
