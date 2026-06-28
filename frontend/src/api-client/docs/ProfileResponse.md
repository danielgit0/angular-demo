
# ProfileResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`firstName` | string
`lastName` | string
`number` | string
`type` | string
`email` | string

## Example

```typescript
import type { ProfileResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "firstName": null,
  "lastName": null,
  "number": null,
  "type": null,
  "email": null,
} satisfies ProfileResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as ProfileResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)
