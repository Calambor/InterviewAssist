# InterviewAssist
Dual purpose interview assistant project

The secondary purpose is to help interviewers conduct semi-structured work interviews while continuously iterating on the quality of the questions asked.

The primary purpose is to experiment with a Kotlin codebase that is shared between Client and Server. This allows me to learn and improve on technologies such as
 - Docker
 - Ktor
 - SQLDelight
 - SQLite
 - TornadoFX

# Modules
## Common
Contains all code that is shared between Server and Client. Ideally this will end up containing most of any error-prone logic and code that require extensive testing
This includes
 - data models
 - contracts between Client and Server
 - database calls

## Desktop client
A cross-platform desktop client built using TornadoFX.
The client uses a local database to maintain full functionality while offline or when ensured privacy is a concern.

## Docker server
A server built with the server framework Ktor.
The server contract aims to be a high quality REST server that allows other clients than the dedicated desktop client to connect.


