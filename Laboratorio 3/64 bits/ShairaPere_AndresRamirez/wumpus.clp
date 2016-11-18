(deftemplate percepcion
	(field elemento)
	(slot direccion)
	(field distancia)
)

(deftemplate heuristica
	(slot costo)
	(slot movimiento)
	(slot encontro)
	(slot murio)
)

(defrule vacio
	(percepcion
		(elemento vacio)
		(direccion ?D)
		(distancia ?d)
	)
	=>
	(assert (heuristica(costo (+ ?d 4))(movimiento ?D)(encontro 0)(murio 0)))

)

(defrule viento
	(percepcion
		(elemento viento)
		(direccion ?D)
		(distancia ?d)
	)
	=>
	(assert (heuristica(costo (+ ?d 5) )(movimiento ?D)(encontro 0)(murio 0)))

)

(defrule olor
	(percepcion
		(elemento olor)
		(direccion ?D)
		(distancia ?d)
	)
	=>
	(assert (heuristica(costo (+ ?d 5) )(movimiento ?D)(encontro 0)(murio 0)))

)


(defrule wumpus
	(percepcion
		(elemento wumpus)
		(direccion ?D)
		(distancia ?d)
	)
	=>
	(assert (heuristica(costo (+ ?d 1000) )(movimiento ?D)(encontro 0)(murio 1)))

)

(defrule precipicio
	(percepcion
		(elemento precipicio)
		(direccion ?D)
		(distancia ?d)
	)
	=>
	(assert (heuristica(costo (+ ?d 1000) )(movimiento ?D)(encontro 0)(murio 1)))

)

(defrule brillo
	(percepcion
		(elemento brillo)
		(direccion ?D)
		(distancia ?d)
	)
	=>
	(assert (heuristica(costo (+ ?d 3) )(movimiento ?D)(encontro 0)(murio 0)))

)

(defrule tesoro
	(percepcion
		(elemento tesoro)
		(direccion ?D)
		(distancia ?d)
	)
	=>
	(assert (heuristica(costo (+ ?d 2) )(movimiento ?D)(encontro 1)(murio 0)))

)

(defrule marcado
	(percepcion
		(elemento limite)
		(direccion ?D)
		(distancia ?d)
	)
	=>
	(assert (heuristica(costo 10000)(movimiento 0)(encontro 0)(murio 0)))
)