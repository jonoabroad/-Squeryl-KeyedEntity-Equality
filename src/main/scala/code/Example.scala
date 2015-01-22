package code

import org.squeryl._
import org.squeryl.dsl._

import org.squeryl.adapters.H2Adapter

case class Thing(val id: Long, val name: String) extends KeyedEntity[Long]
case class Thang(val id: Long, val name: String)

object Entrypoint extends PrimitiveTypeMode

import Entrypoint._

object ExampleSchema extends Schema {

  val things = table[Thing]
  val thangs = table[Thang]

  on(things) { t ⇒
    declare(
      t.id is (primaryKey, unique, indexed("thing_idx"), autoIncremented("s_thing_id")))
  }

//Field id of table Thang is declared as autoIncremented, auto increment is currently only supported on KeyedEntity[A].id  
/*  on(thangs) { t ⇒
    declare(
      t.id is (primaryKey, unique, indexed("thang_idx"), autoIncremented("s_thang_id")))
  } */

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

  println(a == b)
  println(c == d)
  
  transaction {
    ExampleSchema.create
    
  }

}

