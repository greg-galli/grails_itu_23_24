package grails_itu_23_244

import com.itu.mbds.Comment
import com.itu.mbds.Post
import com.itu.mbds.Role
import com.itu.mbds.File
import com.itu.mbds.Tag
import com.itu.mbds.User
import com.itu.mbds.UserRole

class BootStrap {

    def init = { servletContext ->

        // Démarre une transaction programmatique
        UserRole.withTransaction {
            // On crée un rôle admin que l'on attribuera après aux utilisateurs
            def adminRole = new Role(authority: 'ROLE_ADMIN').save()
            // On crée un compte utilisateur
            def adminUser = new User(username: 'admin', password: 'admin').save()

            // On attribue le role (ROLE_ADMIN) à l'utilisateur
            UserRole.create(adminUser, adminRole)
        }

        assert User.count() == 1
        assert Role.count() == 1
        assert UserRole.count() == 1

        Tag.withTransaction {
            // On ajoute 3 tags que l'on va réutiliser ensuite dans les post
            ["Happy", "EnjoySchool", "ITUPowa"].each {
                String tagName ->
                    new Tag(name: tagName).save()
            }
        }

        assert Tag.count() == 3

        Post.withTransaction {

            // On recharche l'utilisateur créé car défini dans une autre transaction
            def adminUser = User.get(1)

            // On ajoute 5 posts à l'utilisateur
            (1..5).each {
                Integer postIdx ->
                    // Information sur le post
                    def postInstance = new Post(title: "Titre du Post $postIdx", content: "Quelque chose")
                    // On ajoute un fichier au post
                    postInstance.file = new File(name: "grails.svg")

                    // On ajoute tous les tags existant au post
                    Tag.list().each {
                        postInstance.addToTags(it)
                    }

                    // On ajoute 5 commentaires au post ainsi qu'à l'utilisateur
                    (1..5).each {
                        def commentInstance = new Comment(content: "Super commentaire plein de positivité")
                        postInstance.addToComments(commentInstance)
                        adminUser.addToComments(commentInstance)
                    }

                    // On ajoute le post à la liste des posts de l'utilisateur
                    adminUser.addToPosts(postInstance)
                    /**
                     *  On sauvegarde l'utilisateur
                     *      Qui sauvegarde les posts
                     *          Qui sauvegarde les commentaires et les fichiers
                     */
                    adminUser.save()
            }
        }

    }
    def destroy = {
    }
}
