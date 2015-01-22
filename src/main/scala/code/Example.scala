package code

import org.squeryl._
import org.squeryl.dsl._

import org.squeryl.adapters.H2Adapter

case class Thing(val id: Long, val name: String) extends KeyedEntity[Long]
case class Thang(val id: Long, val name: String)
case class Solution(val id: Long, val name: String)

object Entrypoint extends PrimitiveTypeMode {

  implicit object personKED extends KeyedEntityDef[Solution, Long] {
    def getId(a: Solution) = a.id
    def isPersisted(a: Solution) = a.id > 0
    def idPropertyName = "id"
  }

}

import Entrypoint._

object ExampleSchema extends Schema {

  val things = table[Thing]
  val thangs = table[Thang]
  val solutions = table[Solution]

  on(things) { t ⇒
    declare(
      t.id is (primaryKey, unique, indexed("thing_idx"), autoIncremented("s_thing_id")))
  }

  //Field id of table Thang is declared as autoIncremented, auto increment is currently only supported on KeyedEntity[A].id  
  /*  on(thangs) { t ⇒
    declare(
      t.id is (primaryKey, unique, indexed("thang_idx"), autoIncremented("s_thang_id")))
  } */

  on(solutions) { t ⇒
    declare(
      t.id is (primaryKey, unique, indexed("solution_idx"), autoIncremented("s_solution_id")))
  }
  
  
  
}

object Example extends App {
  import org.squeryl.SessionFactory
  Class.forName("org.h2.Driver");
  SessionFactory.concreteFactory = Some(() ⇒
    Session.create(
      java.sql.DriverManager.getConnection("jdbc:h2:mem:"),
      new H2Adapter))

  val a = Thing(0, "thing")
  val b = Thing(0, "thing")

  val c = Thang(0, "thang")
  val d = Thang(0, "thang")

  val e = new Solution(0,"solution")
  val f = new Solution(0,"solution")
  
  println(a == b)
  println(c == d)
  println(e == f)  

  transaction {
    ExampleSchema.create
    val s1 = ExampleSchema.solutions.insert(e)
    val s2 = ExampleSchema.solutions.insert(f)
    println(s1)
    println(s2)
    println(s1 == s2)
    
  }

}

