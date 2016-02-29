(ns modern-cljs.login)

;; define the function to be attached to the form submission event
(defn validate-form []
  ;; get email and password elements from the document
  (let [email-element (.getElementById js/document "email")
        password-element (.getElementById js/document "password")]
    (if (and (> (count (.-value email-element)) 0)
             (> (count (.-value password-element)) 0))
      true
      (do (js/alert "Please, complete the form!")
          false))))

;; define the function to attach validate-form to the onsubmit event of the form
(defn init []
  ;; verify that js/document exists and that it has a getElementById property
  (if (and js/document
           (.-getElementById js/document))
    ;; get the loginForm by element id and set its onsubmit property to our validate-form function
    (let [login-form-element (.getElementById js/document "loginForm")]
      (set! (.-onsubmit login-form-element) validate-form))))

(set! (.-onload js/window) init)
