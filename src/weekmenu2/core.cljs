(ns weekmenu2.core
    (:require [reagent.core :as reagent]
              [weekmenu2.components :as components]
              [weekmenu2.containers.calendar :as calendar]))


(defn main-view []
  [:div.container
   [components/title]
   [:div
    [calendar/calendar-view]]]
  )


;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [main-view] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
