(deftemplate percepcion
	(field s1)
	(field s2)
	(field s3)
	(field s4)
	(field s5)
	(field s6)
	(field s7)
	(field s8)
)

(deftemplate movimiento
    (slot direccion)
)




(defrule x1
    (or
	 (percepcion(s2 1)(s3 1))
	 (or
	 (percepcion(s1 0)(s2 1)(s3 0)(s4 0)(s5 0)(s6 0)(s7 0)(s8 0))
	 (percepcion(s1 0)(s2 0)(s3 1)(s4 0)(s5 0)(s6 0)(s7 0)(s8 0)))
	 )
	=>
	(assert(x1))
)

(defrule x2
   (or
	(percepcion(s5 1)(s8 1))
	(or
	 (percepcion(s1 0)(s2 0)(s3 0)(s4 0)(s5 1)(s6 0)(s7 0)(s8 0))
	 (percepcion(s1 0)(s2 0)(s3 0)(s4 0)(s5 0)(s6 0)(s7 0)(s8 1)))
    )
	=>
	(assert(x2))
)

(defrule x3
   (or
	 (percepcion(s6 1) (s7 1))
	(or
	 (percepcion(s1 0)(s2 0)(s3 0)(s4 0)(s5 0)(s6 1)(s7 0)(s8 0))
	 (percepcion(s1 0)(s2 0)(s3 0)(s4 0)(s5 0)(s6 0)(s7 1)(s8 0)))
   )
	=>
	(assert(x3))
)

(defrule x4
     (or
	(percepcion(s1 1) (s4 1))
	(or
	 (percepcion(s1 1)(s2 0)(s3 0)(s4 0)(s5 0)(s6 0)(s7 0)(s8 0))
	 (percepcion(s1 0)(s2 0)(s3 0)(s4 1)(s5 0)(s6 0)(s7 0)(s8 0)))
    )
	=>
	(assert(x4))
)


(defrule norte
	(or
	    (percepcion
	      (s1 0)
	      (s2 0)
	      (s3 0)
	      (s4 0)
	      (s5 0)
	      (s6 0)
	      (s7 0)
	      (s8 0)
	    )
	    (or
		    (and (x4) (not(x1)))
		    (percepcion(s1 0)(s2 0)(s3 0)(s4 1)(s5 0)(s6 1)(s7 0)(s8 0))
		)
    )

 =>(assert(movimiento(direccion 0)))
)


(defrule este
 (or
  (and (x1) (not(percepcion(s5 1)(s8 1))  ) )
  (percepcion(s1 1)(s2 1)(s3 0)(s4 0)(s5 0)(s6 0)(s7 0)(s8 0)) 
 )
  =>(assert(movimiento(direccion 1)))
)

(defrule sur
 (or
  (and (x2) (not(percepcion(s6 1) (s7 1))) )	
  (percepcion(s1 0)(s2 0)(s3 1)(s4 0)(s5 1)(s6 0)(s7 0)(s8 0))
  )
  =>(assert(movimiento(direccion 2)))
)


(defrule oeste
 (or
  (and (x3) (not(x4)) )
  (percepcion(s1 0)(s2 0)(s3 0)(s4 0)(s5 0)(s6 0)(s7 1)(s8 1))
  )
  =>(assert(movimiento(direccion 3)))
)