# Frontend Development Architecture Template (with Kobweb, Koin, Ktor)

## Introduction

This is a template extended from the [`app/empty`](https://github.com/varabyte/kobweb-templates/tree/d151f08a366cef4f44f344ff8e3368c00e2c2955/app/empty) template of the [Kobweb Framework](https://github.com/varabyte/kobweb).
It includes some additional libraries and architecture that I believe are essential for full frontend development.

The architecture basically includes having repositories, models, and a controller (some other architectures call this a view model, presenter, etc.). Additionally, it demonstrates using DI (using Koin).

It is a simpler version of [clean architecture](https://medium.com/better-programming/the-clean-architecture-beginners-guide-e4b7058c1165), which advises using UseCases (not a fan of creating one unless it contains code repeated in multiple view models/controllers).

On top of this, it includes an `Async` interface, an extended version of the `kotlin.Result` (implementation of monad in Kotlin) interface. This interface has been determined as essential based on practical experience with FE development. 

A `Result` interface (`Optional` in Java, etc.) represents only two states (value is present or not), but the `Async` interface extends the non-availability part of the monad to include that the computation has not started yet (`Async.Uninitialized`) or the computation is in progress (`Async.Loading`). While the `Async.Uninitialized` is not always needed or can be represented with `null` (or it is when dealing with `null` is unpleasant), the `Async.Loading` is essential for communicating to the user that the computation is in progress — a state that is encountered almost every time in FE development.

The `Async` interface is lifted from the [MvRx](https://github.com/airbnb/mavericks/blob/main/mvrx-common/src/main/java/com/airbnb/mvrx/Async.kt) codebase but has implementations from other resources (e.g., the [`Resource` class](https://gist.github.com/idrisadetunmbi/758a3e26427f4b69fc5c0baed9415a93) from the old guide to app architecture) and can easily be written based on preference.

Another essential is the `BasePageModel`, an abstract class that a controller can extend for UI state management incorporating immutability (required for a deterministic behaviour of the UI).

The template does not include "do it if you think you need it" things like modularization and separate models for different layers.

## Getting Started

Clone/fork and adapt to your project.

## Contributing

Contributions are welcome! Create a pull requested if interested.

## License

This project is in the public domain. See the [LICENSE](LICENSE) file for details.
