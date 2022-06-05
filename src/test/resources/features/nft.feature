Feature: nft generation

  Scenario: An NFT is generated
    Given the following layers
      | imageId | width | height | xOffset | yOffset | zOrder |
      | 1       | 400   | 400    | 0       | 0       | 1      |
      | 2       | 400   | 400    | 0       | 0       | 4      |
      | 5       | 400   | 400    | 0       | 0       | 3      |
      | 7       | 400   | 400    | 0       | 0       | 2      |
    When the user requests all the layers
    Then all the layers are returned