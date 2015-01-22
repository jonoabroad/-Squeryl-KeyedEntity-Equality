A quick example outlining an issue I am having in [Squeryl](Squeryl.org). 

To test run  `sbt run`. The output should look similar to:

    $ sbtNR run
    [info] Loading global plugins from /Users/jonoabroad/.sbt/0.13/plugins
    [info] Loading project definition from /Users/jonoabroad/developer/scala/squerlyKeyedEntityEquality/project
    [info] Set current project to Squeryl KeyedEntity Equality (in build file:/Users/jonoabroad/developer/scala/squerlyKeyedEntityEquality/)
    [info] Running code.Example 
    false
    true
    [success] Total time: 1 s, completed 22/01/2015 2:32:41 PM

The issue is the `false`.  This is due to comparing two instances of a case class which extends `KeyedEntity[T]`.  The `KeyedEntity` trait overrides `equals` squashing the compiler implemented version of the method with one that does reference equality as it's final comparison. 

This causes issues when testing functionality which returns instances of the class - as they will always fail.  One would need to either implement our own version of `equals` which concerns me as I'm not aware of the consequences as to how Squeryl would react. The second is to write tests the check each field in the instances manually.  This concerns me as it doesn't let the compiler help with additions to the class in the future. 

We thought removing the trait may be an option, but our dependency on an auto incremented primary key means we don't see this as being an option. 
 