
# ErrorMessageMeta


## Properties

Name | Type
------------ | -------------
`requestUri` | string
`queryString` | string
`exception` | string

## Example

```typescript
import type { ErrorMessageMeta } from ''

// TODO: Update the object below with actual values
const example = {
  "requestUri": null,
  "queryString": null,
  "exception": null,
} satisfies ErrorMessageMeta

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as ErrorMessageMeta
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)
