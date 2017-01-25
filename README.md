# bigcommerce-fetch

## About backend
It's a simple application for quering API of Bigcommerce platform and flatten 
it's results for frontend consumer, backend part is written in Groovy using 
Spring Boot with Spring Data REST, Spring Data itself, also uses some 
simple scheduling which is also included in Spring Boot. 
For unit testing there is a embeded MongoDB and Spock Framework.

## About frontend
Frontend was made using React + Redux stack with bootstrap and others helper techs. 
React + Redux + Reselect + redux-thunk + redux-promise-middleware + amazing create-react-app and some other stuff.

Frontend for sure is not a good example of CSS styling since it's Frankenstein with tonns of global CSS styling coming with website of the client, so it have billions of horrible hardcoded pixel widths's
by id for each cell. But it's okay for this case anyway :)
It's fine example of usage Autosuggest component from https://github.com/moroshko/react-autosuggest
Some pieces of frontend is messy 'cause there is no reason to huge refactoring yet :) But backend is fine :) At least I'm a fulltime backend developer, don't blame me much :)

## How to launch
Run Spring Boot app using the IDE of your choice. 
Then for frontend simply in directory with frontend package.json run
```
npm start 
```
And there you have it. It's in dev mode of course that's why performance is not the best, but in production (to build `npm run build` and follow instruction in CLI) it works just fine.

