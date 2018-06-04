(ns json2xlsx.core
  (:gen-class)
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.java.io :refer [delete-file]]
            [cheshire.core :refer [parse-stream]]
            [dk.ative.docjure.spreadsheet :refer [create-workbook
                                                  save-workbook!]]))

(def ^:private cli-options
  [["-i" "--input FILE" "Input file"
    :default "-"]
   ["-o" "--output FILE" "Output file"
    :default "out.xlsx"]
   ["-v" nil "Verbosity level"
    :id :verbosity
    :default 0
    :assoc-fn (fn [m k _] (update-in m [k] inc))]
   ["-h" "--help"]])

(defn- source
  "Returns *in* if `filename` is `-`, otherwise returns a reader for
  `filename`"
  [filename]
  (if (= filename "-")
    *in*
    (clojure.java.io/reader filename)))

(defn -main
  [& args]
  (let [opts (parse-opts args cli-options)
        output (-> opts :options :output)]
    (when (-> opts :options :help)
      (-> opts :summary println)
      (System/exit 0))
    (->> opts
         :options
         :input
         source
         parse-stream
         (create-workbook "Sheet")
         ;; TODO before, check against spec
         (save-workbook! output))
    (println (str "XSLX written to: " output))))
