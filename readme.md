# Netherdrake

[![Download](https://api.bintray.com/packages/wynsel/maven/netherdrake/images/download.svg?version=0.0.2) ](https://bintray.com/wynsel/maven/netherdrake/0.0.2/link)

Ah yes. The utility hero that can carry, gank and support. 

Anyway, this is a VIPER architecture library for Android that's easy to implement (maybe) and very manageable. This is a modularized style architecture, where one **V** must have its own **IPER** inside a package.

Now, each of us have a preferred style for implementing this architecture. Well, sorry to disappoint you but I like this style. If you don't like this, then go find other library that suits your style. I'm not Santa Claus. But, if you like this style, then proceed.

## Installation
Easy peasy. Lemon squeezy.

```groovy
implementation 'com.wynsel.netherdrake:netherdrake:0.0.2'
```

## Usage

First, you need to create a package that defines the feature you want to implement in this view. This can be main, add something, show something. It's up to you. Right-click your packaging id, then **New** -> **Package**

Create three classes inside your newly created package. The names must relate to the package name, then add **Presenter/Interactor** suffixes.

e.g.

* AddPersonActivity
* AddPersonPresenter
* AddPersonInteractor 

Now, Extend 'em all.

### View
```kotlin
// Activity
class AddPersonActivity: NetherActivity<AddPersonPresenter>() {

    override val getLayout: Int = R.layout.activity_add_person
    override val initializePresenter: AddPersonPresenter = AddPersonPresenter(this)

    override fun onViewReady() {

    }

    /*
    	Add modules below here!
    */
}
```

```kotlin
// Fragment
class AddPersonFragment: NetherFragment<AddPersonPresenter>() {

    override val getLayout: Int = R.layout.fragment_add_person
    override val initializePresenter: AddPersonPresenter = AddPersonPresenter(this)

    override fun beforeCreateView() {
        
    }

    override fun onViewReady() {
        
    }

    /*
    	Add modules below here!
    */
}
```

```kotlin
// Dialog
class AddPersonDialog: NetherDialog<AddPersonPresenter, AddPersonActivity.ViewHolder>() {

    override val getLayout: Int = R.layout.dialog_add_person
    override val initializePresenter: AddPersonPresenter = AddPersonPresenter(this)

    override val positiveButton: Buttons = Buttons("Ok", true) { d, w ->
        /*
        	Positive click
        */
    }
    override val negativeButton: Buttons? = null
    override val neutralButton: Buttons? = null
    
    override fun onCreateViewHolder(view: View): ViewHolder = ViewHolder(view)

    override fun onViewReady() {

    }

    inner class ViewHolder(v: View): NetherViewHolder(v) {
    	/*
    		Prepare forms
    	*/
    }

    /*
    	Add modules below here!
    */
}
```
Yeah, I know. I intentionally added view holder feature in this because of custom views.

### Presenter

```kotlin
class AddPersonPresenter(view: AddPersonActivity): NetherPresenter<AddPersonActivity, AddPersonInteractor>(view) {

    override val initializeInteractor: AddPersonInteractor = AddPersonInteractor(this)

    // Manually set the router class to support routing.
    private val router = NetherRouter(baseActivity)

    override fun onPresenterReady() {

    }

    override fun onPresenterEnd() {

    }

    override fun afterActivitySet() {

    }

    /*
    	Add modules below here!
    */
}
```
The presenter can support interactor and router modules. 

### Interactor

```kotlin
class AddPersonInteractor(presenter: AddPersonPresenter): NetherInteractor<AddPersonPresenter>(presenter) {
    override fun onInteractorReady() {

    }

    /*
    	Add modules below here!
    */
}
```
